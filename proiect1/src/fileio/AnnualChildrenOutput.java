package fileio;

import java.util.ArrayList;

public final class AnnualChildrenOutput {
    private final ArrayList<ChildListOutput> annualChildren;

    public AnnualChildrenOutput() {
        annualChildren = new ArrayList<>();
    }

    public void addChildrenList(final ChildListOutput list) {
        annualChildren.add(list);
    }

    public ArrayList<ChildListOutput> getAnnualChildren() {
        return annualChildren;
    }
}
