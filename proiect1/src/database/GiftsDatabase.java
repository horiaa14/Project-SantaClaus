package database;

import fileio.GiftInputData;
import fileio.UpdateInputData;
import gift.Gift;
import simulation.Observer;

import java.util.ArrayList;

public final class GiftsDatabase implements Observer {
    private final ArrayList<Gift> gift;
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

    /**
     * The method is used to build the database that stores Santa's gifts
     * for children. We go through the list of gifts that Santa Claus has
     * before the simulation and add them to the database.
     * @param gifts represents the initial list of gifts
     */
    public void buildGiftsDatabase(final ArrayList<GiftInputData> gifts) {
        for (GiftInputData currGift : gifts) {
            gift.add(new Gift(currGift));
        }
    }

    @Override
    public void update(final UpdateInputData annualChange) {
        ArrayList<GiftInputData> newGifts = annualChange.getNewGifts();
        for (GiftInputData currGift : newGifts) {
            Gift newGift = new Gift(currGift.getProductName(),
                                   currGift.getPrice(), currGift.getCategory());

            gift.add(newGift);
        }
    }

    public ArrayList<Gift> getGift() {
        return gift;
    }
}
