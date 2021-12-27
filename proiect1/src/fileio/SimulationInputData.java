package fileio;

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

    @Override
    public String toString() {
        return "SimulationInputData{"
                + "numberOfYears=" + numberOfYears
                + ", santaBudget=" + santaBudget
                + '}';
    }
}
