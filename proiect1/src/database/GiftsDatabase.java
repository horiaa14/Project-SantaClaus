package database;

import gift.Gift;

import java.util.ArrayList;

public class GiftsDatabase {
    private ArrayList<Gift> gift;
    private static GiftsDatabase instance = null;

    private GiftsDatabase() {
        gift = new ArrayList<>();
    }

    public static GiftsDatabase getUniqueInstance() {
        if (instance == null) {
            instance = new GiftsDatabase();
        }

        return instance;
    }
}
