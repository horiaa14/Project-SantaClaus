package database;

import children.Child;

import java.util.ArrayList;

public class ChildrenDatabase {
    private ArrayList<Child> child;
    private static ChildrenDatabase instance = null;

    private ChildrenDatabase() {
        this.child = new ArrayList<>();
    }

    public static ChildrenDatabase getUniqueInstance() {
        if (instance == null) {
            instance = new ChildrenDatabase();
        }

        return instance;
    }
}
