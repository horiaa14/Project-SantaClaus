package fileio;

import java.util.ArrayList;

/**
 * In this class we find the necessary fields for reading information about
 * an update.
 */

public final class UpdateInputData {
    private final Double newSantaBudget;
    private final ArrayList<GiftInputData> newGifts;
    private final ArrayList<ChildInputData> newChildren;
    private final ArrayList<ChildUpdateInputData> childrenUpdates;


    public UpdateInputData(final Double newSantaBudget,
                           final ArrayList<GiftInputData> newGifts,
                           final ArrayList<ChildInputData> newChildren,
                           final ArrayList<ChildUpdateInputData> childrenUpdates) {

        this.newSantaBudget = newSantaBudget;
        this.newGifts = newGifts;
        this.newChildren = newChildren;
        this.childrenUpdates = childrenUpdates;
    }

    public Double getNewSantaBudget() {
        return newSantaBudget;
    }

    public ArrayList<GiftInputData> getNewGifts() {
        return newGifts;
    }

    public ArrayList<ChildInputData> getNewChildren() {
        return newChildren;
    }

    public ArrayList<ChildUpdateInputData> getChildrenUpdates() {
        return childrenUpdates;
    }
}
