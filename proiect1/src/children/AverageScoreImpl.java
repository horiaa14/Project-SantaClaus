package children;

import common.Constants;

import java.util.ArrayList;

public final class AverageScoreImpl implements AverageScoreCalculator {
    /**
     * This method calculates the average score for a Baby object
     * @return a constant Double value (10.0)
     */
    public Double getAverageScore(final Baby baby) {
        return Constants.AVERAGE_SCORE_BABY;
    }

    /**
     * This method calculates the average score for a Kid object
     * I used streams to perform this calculation.
     * @return a Double value obtained by calculating the arithmetic mean
     * of the values in the score list
     */
    public Double getAverageScore(final Kid kid) {
        ArrayList<Double> niceScoreList = kid.getNiceScoreHistory();
        return niceScoreList.stream().mapToDouble(Double::doubleValue).sum()
                / niceScoreList.size();
    }

    /**
     * This method calculates the average score for a Teen object.
     * I calculated the weighted average of the scores from the score list
     * according to the rules specified in the statement.
     * @return a Double value that represents the average score.
     */
    public Double getAverageScore(final Teen teen) {
        double num = 0;
        double den;
        ArrayList<Double> niceScoreList = teen.getNiceScoreHistory();
        for (int i = 0; i < niceScoreList.size(); ++i) {
            num += (niceScoreList.get(i) * (i + 1));
        }

        den = (niceScoreList.size() * (1 + niceScoreList.size())) / 2.0;
        return (num / den);
    }
}
