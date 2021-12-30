package fileio;

import children.Child;

import java.util.ArrayList;

public final class ChildListOutput {
    private final ArrayList<ChildOutput> children;

    public ChildListOutput() {
        children = new ArrayList<>();
    }

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
