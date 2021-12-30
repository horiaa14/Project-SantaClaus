package children;

import enums.Category;
import enums.Cities;

import java.util.LinkedList;

public final class Baby extends Child implements ChildInterface {
    /*public Baby(final Integer id, final String lastName,
                 final String firstName,
                final Integer age, final Cities city, final Double niceScore,
                final LinkedList<Category> giftPreferences) {
        super(id, lastName, firstName, age, city, niceScore, giftPreferences);
    }*/

    public Baby() {
        super();
    }

    @Override
    public Double accept(final AverageScoreCalculator calculator) {
        return calculator.getAverageScore(this);
    }
}
