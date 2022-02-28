package fileio;

import children.Child;

import java.util.ArrayList;

/**
 * This class contains as a member a list that will contain information about
 * all the children who received gifts after a round.
 */

public final class ChildListOutput {
    private final ArrayList<ChildOutput> children;

    public ChildListOutput() {
        children = new ArrayList<>();
    }

    /**
     * In this method we copy the information about all the children who
     * received gifts after simulating a round. These objects will be added
     * to the children field of the class.
     * @param child the list of children who received gifts after the simulation
     */
    public void completeList(final ArrayList<Child> child) {
        for (Child currChild : child) {
            ChildOutput newChild = new ChildOutput(currChild);
            children.add(newChild);
        }
    }

    public ArrayList<ChildOutput> getChildren() {
        return children;
    }
}
