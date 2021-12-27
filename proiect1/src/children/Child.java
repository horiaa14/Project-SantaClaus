package children;

import enums.Category;
import enums.Cities;
import gift.Gift;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Child implements ChildInterface {
    private Integer id;
    private String lastName;
    private String firstName;
    private Integer age;
    private Cities city;
    private Double niceScore;
    private LinkedList<Category> giftPreferences;
    private Double averageScore;
    private ArrayList<Double> niceScoreHistory;
    private Double assignedBudget;
    private ArrayList<Gift> receivedGifts;

    public Child() {

    }

    public Child(final Integer id, final String lastName,
                 final String firstName, final Integer age, final Cities city,
                 final Double niceScore,
                 final LinkedList<Category> giftPreferences) {

        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.city = city;
        this.niceScore = niceScore;
        this.giftPreferences = giftPreferences;

        this.niceScoreHistory = new ArrayList<>();
        this.niceScoreHistory.add(niceScore);
        this.receivedGifts = new ArrayList<>();
    }

    @Override
    public abstract Double accept(AverageScoreCalculator calculator);

    public final ArrayList<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }
}
