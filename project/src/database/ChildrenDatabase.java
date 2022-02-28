package database;

import children.Child;
import children.ChildFactory;
import common.Constants;
import enums.Category;
import fileio.ChildInputData;
import fileio.ChildUpdateInputData;
import fileio.UpdateInputData;
import simulation.Observer;
import simulation.Simulation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Iterator;

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

    /**
     * The method builds the database with children before the simulation
     * starts. These children will be kept on a list of type Child. We will
     * generate objects that are of types derived from the Child class, and
     * we will add them to the list (upcast). Generating these objects uses
     * the Abstract Object Factory design pattern.
     * @param children the list of children that we will add to the database
     */
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

    /**
     * In this method we will go through the list of children and update
     * their age. If the new age falls into another category it will be
     * necessary to change the child's reference to another derived object.
     * To identify the type of child according to the age category we will use
     * the Abstract Object Factory design pattern.
     */
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

        // Children who have become young adults will need to be removed.
        child.removeAll(Collections.singletonList(null));
    }

    /**
     * The method is used to add new children to the database during
     * update operations.
     * @param newChildren the list of children to be added to the database
     */
    public void addNewChildren(final ArrayList<ChildInputData> newChildren) {
        for (ChildInputData newChildInput : newChildren) {
            Child newChild = ChildFactory.createChild(newChildInput.getAge());
            if (newChild != null) {
                newChild.setFields(newChildInput);
                child.add(newChild);
            }
        }
    }

    /**
     * The purpose of the method is to check whether a child is in the database
     * or not. The search will be based on the id.
     * @param id is the id of the child we are looking for
     * @return the position of the child in the list or INVALID_POSITION
     *         if it does not exist.
     */
    public int contains(final Integer id) {
        for (int i = 0; i < child.size(); ++i) {
            if (Objects.equals(child.get(i).getId(), id)) {
                return i;
            }
        }
        return Constants.INVALID_POSITION;
    }

    /**
     * In this method we will go through the list of updates and update
     * the database if necessary. For the current child we will only make
     * updates if it is in the database. Adding a new item to the score list
     * will only be done if that score is not null. To change the list of
     * preferences we must first remove duplicates from the new list of
     * preferences. We remove the preferences that appear in the two lists and
     * add the new preferences to the existing ones.
     * @param childrenUpdates a list of updates about children
     */
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
                Simulation.removeDuplicates(newPreferences);
                category.removeAll(newPreferences);

                /*We go through the list of new preferences in reverse order
                and add its elements to the top of the list of preferences.*/

                Iterator<Category> it = newPreferences.descendingIterator();
                while (it.hasNext()) {
                    category.addFirst(it.next());
                }
            }
        }
    }

    /**
     * This method will be called at the end of a round. As this class plays
     * the role of an observer, it is necessary to update the information
     * about the children.
     * @param annualChange an object that holds information about changes that
     *                     are made before the next round.
     */
    @Override
    public void update(final UpdateInputData annualChange) {
        increaseAge();
        addNewChildren(annualChange.getNewChildren());
        updateChildren(annualChange.getChildrenUpdates());
    }

    public ArrayList<Child> getChild() {
        return child;
    }
}
