package database;

import children.Child;

import java.util.ArrayList;

public final class ChildrenDatabase {
    private ArrayList<Child> child;
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
}
