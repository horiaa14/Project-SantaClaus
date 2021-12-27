package fileio;

import java.util.ArrayList;

public final class Input {
    private final SimulationInputData simulationData;
    private final ArrayList<ChildInputData> childrenData;
    private final ArrayList<GiftInputData> giftsData;
    private final ArrayList<UpdateInputData> annualChangesData;

    public Input() {
        this.simulationData = null;
        this.childrenData = null;
        this.giftsData = null;
        this.annualChangesData = null;
    }

    public Input(final SimulationInputData simulationData,
                 final ArrayList<ChildInputData> childrenData,
                 final ArrayList<GiftInputData> giftsData,
                 final ArrayList<UpdateInputData> annualChangesData) {
        this.simulationData = simulationData;
        this.childrenData = childrenData;
        this.giftsData = giftsData;
        this.annualChangesData = annualChangesData;
    }

    public SimulationInputData getSimulationData() {
        return simulationData;
    }

    public ArrayList<ChildInputData> getChildrenData() {
        return childrenData;
    }

    public ArrayList<GiftInputData> getGiftsData() {
        return giftsData;
    }

    public ArrayList<UpdateInputData> getAnnualChangesData() {
        return annualChangesData;
    }

    @Override
    public String toString() {
        return "Input{" +
                "simulationData=" + simulationData +
                ", childrenData=" + childrenData +
                ", giftsData=" + giftsData +
                ", annualChangesData=" + annualChangesData +
                '}';
    }
}
