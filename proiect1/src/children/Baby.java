package children;

import enums.Category;
import enums.Cities;

import java.util.LinkedList;

public class Baby extends Child implements ChildInterface{
    public Baby(Integer id, String lastName, String firstName, Integer age,
                Cities city, Double niceScore,
                LinkedList<Category> giftPreferences) {
        super(id, lastName, firstName, age, city, niceScore, giftPreferences);
    }

    public Baby() {
        super();
    }

    @Override
    public Double accept(AverageScoreCalculator calculator) {
        return calculator.getAverageScore(this);
    }
}
