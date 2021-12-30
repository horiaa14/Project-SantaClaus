package children;

public final class Baby extends Child implements ChildInterface {
    public Baby() {
        super();
    }

    @Override
    public Double accept(final AverageScoreCalculator calculator) {
        return calculator.getAverageScore(this);
    }
}
