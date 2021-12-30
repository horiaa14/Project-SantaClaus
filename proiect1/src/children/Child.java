package children;

import enums.Category;
import enums.Cities;
import fileio.ChildInputData;
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

    @Override
    public String toString() {
        return "Child{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", age=" + age +
                ", city=" + city +
                ", niceScore=" + niceScore +
                ", giftPreferences=" + giftPreferences +
                ", averageScore=" + averageScore +
                ", niceScoreHistory=" + niceScoreHistory +
                ", assignedBudget=" + assignedBudget +
                ", receivedGifts=" + receivedGifts +
                '}';
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void copyFields(Child child) {
        this.id = child.id;
        this.lastName = child.lastName;
        this.firstName = child.firstName;
        this.age = child.age;
        this.city = child.city;
        this.niceScore = child.niceScore;
        this.giftPreferences = child.giftPreferences;
        this.averageScore = child.averageScore;
        this.niceScoreHistory = child.niceScoreHistory;
        this.assignedBudget = child.assignedBudget;
        this.receivedGifts = child.receivedGifts;
    }

    public void setFields(ChildInputData child) {
        this.id = child.getId();
        this.lastName = child.getLastName();
        this.firstName = child.getFirstName();
        this.age = child.getAge();
        this.city = child.getCity();
        this.niceScore = child.getNiceScore();
        this.giftPreferences = child.getGiftPreferences();
        this.niceScoreHistory = new ArrayList<>();
        this.receivedGifts = new ArrayList<>();
        this.niceScoreHistory.add(niceScore);
    }

    @Override
    public abstract Double accept(AverageScoreCalculator calculator);

    public final ArrayList<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    public final void setAverageScore(Double averageScore) {
        this.averageScore = averageScore;
    }

    public final Double getAverageScore() {
        return averageScore;
    }

    public final Integer getAge() {
        return age;
    }

    public final ArrayList<Gift> getReceivedGifts() {
        return receivedGifts;
    }

    public final Double getAssignedBudget() {
        return assignedBudget;
    }

    public final LinkedList<Category> getGiftPreferences() {
        return giftPreferences;
    }

    public Cities getCity() {
        return city;
    }

    public Double getNiceScore() {
        return niceScore;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public final void addNiceScore(final Double score) {
        niceScoreHistory.add(score);
    }

    public Integer getId() {
        return id;
    }

    public final void setAssignedBudget(Double assignedBudget) {
        this.assignedBudget = assignedBudget;
    }
}
