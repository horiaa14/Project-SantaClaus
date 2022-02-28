package fileio;

import java.util.ArrayList;

/**
 * This class is used to display information about children who have
 * received gifts throughout the simulation.
 */

public final class AnnualChildrenOutput {
    private final ArrayList<ChildListOutput> annualChildren;

    public AnnualChildrenOutput() {
        annualChildren = new ArrayList<>();
    }

    /**
     * The role of this method is to add the list of children who received
     * gifts in the current round to the list for all years in the simulation.
     * @param list represents the list of children who received gifts after
     *             simulating a round.
     */
    public void addChildrenList(final ChildListOutput list) {
        annualChildren.add(list);
    }

    public ArrayList<ChildListOutput> getAnnualChildren() {
        return annualChildren;
    }
}
