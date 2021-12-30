package database;

import children.Child;
import children.ChildFactory;
import common.Constants;
import enums.Category;
import fileio.ChildInputData;
import fileio.ChildUpdateInputData;
import fileio.UpdateInputData;
import simulation.Observer;
import utils.Utils;

import java.util.*;

public final class ChildrenDatabase implements Observer {
    private final ArrayList<Child> child;
    private static ChildrenDatabase instance = null;

    private ChildrenDatabase() {
        this.child = new ArrayList<>();
    }

    /**
     * This method is used to implement the Singleton design pattern for
     * the database in which the children will be stored.
     * @return a ChildrenDatabase class type reference.
     */
    public static ChildrenDatabase getUniqueInstance() {
        if (instance == null) {
            instance = new ChildrenDatabase();
        }

        return instance;
    }

    public void increaseAge() {
        for (int i = 0; i < child.size(); ++i) {
            Child currChild = child.get(i);
            currChild.setAge(1 + currChild.getAge());
            Child newChild = ChildFactory.createChild(currChild.getAge());

            if (newChild != null) {
                newChild.copyFields(currChild);
            }
            child.set(i, newChild);
        }

        child.removeAll(Collections.singletonList(null));
    }

    public void addNewChildren(final ArrayList<ChildInputData> newChildren) {
        for (ChildInputData newChildInput : newChildren) {
            Child newChild = ChildFactory.createChild(newChildInput.getAge());
            if (newChild != null) {
                newChild.setFields(newChildInput);
                child.add(newChild);
            }
        }
    }

    public int contains(final Integer id) {
        for (int i = 0; i < child.size(); ++i) {
            if (Objects.equals(child.get(i).getId(), id)) {
                return i;
            }
        }
        return Constants.INVALID_POSITION;
    }

    @Override
    public String toString() {
        return "ChildrenDatabase{" +
                "child=" + child +
                '}';
    }

    public void updateChildren(final ArrayList<ChildUpdateInputData> childrenUpdates) {
        for (ChildUpdateInputData currUpdate : childrenUpdates) {
            int pos = contains(currUpdate.getId());
            if (pos != Constants.INVALID_POSITION) {
                if (currUpdate.getNiceScore() != null) {
                    child.get(pos).addNiceScore(currUpdate.getNiceScore());
                }

                LinkedList<Category> category = child.get(pos).getGiftPreferences();
                LinkedList<Category> newPreferences =
                        currUpdate.getGiftsPreferences();
                Utils.removeDuplicates(newPreferences);
                category.removeAll(newPreferences);
                Iterator<Category> it = newPreferences.descendingIterator();
                while (it.hasNext()) {
                    category.addFirst(it.next());
                }
            }
        }
    }

    @Override
    public void update(UpdateInputData annualChange) {
        increaseAge();
        addNewChildren(annualChange.getNewChildren());
        updateChildren(annualChange.getChildrenUpdates());
    }

    public void buildChildrenDatabase(final ArrayList<ChildInputData> children) {
        for (ChildInputData currChild : children) {
            Integer age = currChild.getAge();
            if (age <= Constants.MAX_AGE_TEEN) {
                Child newChild = ChildFactory.createChild(age); // UPCASTING
                assert newChild != null;
                newChild.setFields(currChild);
                child.add(newChild);
            }
        }
    }

    public ArrayList<Child> getChild() {
        return child;
    }
}
