package fileio;

/**
 * This class is used to read general information about simulation:
 * the number of years and Santa's initial budget.
 */

public final class SimulationInputData {
    private final Integer numberOfYears;
    private final Double santaBudget;

    public SimulationInputData(final Integer numberOfYears,
                               final Double santaBudget) {
        this.numberOfYears = numberOfYears;
        this.santaBudget = santaBudget;
    }

    public Integer getNumberOfYears() {
        return numberOfYears;
    }

    public Double getSantaBudget() {
        return santaBudget;
    }
}
