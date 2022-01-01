package fileio;

import java.util.ArrayList;

/**
 * This class contains fields for reading all the information from the input
 * files. After parsing a json file, we will return an object of type
 * Input
 */

public final class Input {
    private final SimulationInputData simulationData;
    private final ArrayList<ChildInputData> childrenData;
    private final ArrayList<GiftInputData> giftsData;
    private final ArrayList<UpdateInputData> annualChangesData;

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
}
