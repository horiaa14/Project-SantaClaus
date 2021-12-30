package children;

public final class Teen extends Child implements ChildInterface {
    public Teen() {
        super();
    }

    @Override
    public Double accept(final AverageScoreCalculator calculator) {
        return calculator.getAverageScore(this);
    }
}
