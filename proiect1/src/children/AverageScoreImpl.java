package children;

import common.Constants;

import java.util.ArrayList;

public class AverageScoreImpl implements AverageScoreCalculator {
    public Double getAverageScore(final Baby baby) {
        return Constants.AVERAGE_SCORE_BABY;
    }

    public Double getAverageScore(final Kid kid) {
        ArrayList<Double> niceScoreList = kid.getNiceScoreHistory();
        return niceScoreList.stream().mapToDouble(Double::doubleValue).sum() /
                niceScoreList.size();
    }

    public Double getAverageScore(final Teen teen) {
        double num = 0;
        double den;
        ArrayList<Double> niceScoreList = teen.getNiceScoreHistory();
        for (int i = 0; i < niceScoreList.size(); ++i) {
            num += niceScoreList.get(i) * (i + 1);
        }

        den = (niceScoreList.size() * (1 + niceScoreList.size())) / 2.0;
        return (num / den);
    }
}
