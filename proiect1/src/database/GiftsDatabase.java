package database;

import gift.Gift;

import java.util.ArrayList;

public final class GiftsDatabase {
    private ArrayList<Gift> gift;
    private static GiftsDatabase instance = null;

    private GiftsDatabase() {
        gift = new ArrayList<>();
    }

    /**
     * This method is used to implement the Singleton design pattern for
     * the database in which the gifts will be stored.
     * @return a GiftsDatabase class type reference.
     */
    public static GiftsDatabase getUniqueInstance() {
        if (instance == null) {
            instance = new GiftsDatabase();
        }

        return instance;
    }
}
