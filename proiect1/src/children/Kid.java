package children;

public final class Kid extends Child implements ChildInterface {
    public Kid() {
        super();
    }

    @Override
    public Double accept(final AverageScoreCalculator calculator) {
        return calculator.getAverageScore(this);
    }
}
