package fileio;

import enums.Category;

import java.util.LinkedList;

/**
 * This class is used to read updates about children
 */

public final class ChildUpdateInputData {
    private final Integer id;
    private final Double niceScore;
    private final LinkedList<Category> giftsPreferences;

    public ChildUpdateInputData(final Integer id, final Double niceScore,
                                final LinkedList<Category> giftsPreferences) {
        this.id = id;
        this.niceScore = niceScore;
        this.giftsPreferences = giftsPreferences;
    }

    public Integer getId() {
        return id;
    }

    public Double getNiceScore() {
        return niceScore;
    }

    public LinkedList<Category> getGiftsPreferences() {
        return giftsPreferences;
    }
}
