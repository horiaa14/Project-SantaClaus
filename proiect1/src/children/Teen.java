package children;

import enums.Category;
import enums.Cities;

import java.util.LinkedList;

public class Teen extends Child implements ChildInterface{

    public Teen(Integer id, String lastName, String firstName, Integer age,
                Cities city, Double niceScore,
                LinkedList<Category> giftPreferences) {
        super(id, lastName, firstName, age, city, niceScore, giftPreferences);
    }

    public Teen() {
        super();
    }

    @Override
    public Double accept(AverageScoreCalculator calculator) {
        return calculator.getAverageScore(this);
    }
}
