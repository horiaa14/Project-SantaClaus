package fileio;

import children.Child;
import enums.Category;
import enums.Cities;
import gift.Gift;

import java.util.ArrayList;
import java.util.LinkedList;

public final class ChildOutput {
    private final Integer id;
    private final String lastName;
    private final String firstName;
    private final Cities city;
    private final Integer age;
    private final LinkedList<Category> giftsPreferences;
    private final Double averageScore;
    private final ArrayList<Double> niceScoreHistory;
    private final Double assignedBudget;

    private final ArrayList<Gift> receivedGifts;

    public ChildOutput(final Child child) {
        this.id = child.getId();
        this.lastName = child.getLastName();
        this.firstName = child.getFirstName();
        this.city = child.getCity();
        this.age = child.getAge();
        this.giftsPreferences = new LinkedList<>();
        this.giftsPreferences.addAll(child.getGiftPreferences());
        this.averageScore = child.getAverageScore();
        this.niceScoreHistory = new ArrayList<>();
        this.niceScoreHistory.addAll(child.getNiceScoreHistory());
        this.assignedBudget = child.getAssignedBudget();
        this.receivedGifts = new ArrayList<>();
        for (Gift gift : child.getReceivedGifts()) {
            this.receivedGifts.add(new Gift(gift));
        }
    }

    public Integer getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Cities getCity() {
        return city;
    }

    public Integer getAge() {
        return age;
    }

    public LinkedList<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public ArrayList<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    public Double getAssignedBudget() {
        return assignedBudget;
    }

    public ArrayList<Gift> getReceivedGifts() {
        return receivedGifts;
    }
}
