package fileio;

import enums.Category;
import enums.Cities;

import java.util.LinkedList;

public final class ChildInputData {
    private final Integer id;
    private final String lastName;
    private final String firstName;
    private final Integer age;
    private final Cities city;
    private final Double niceScore;
    private final LinkedList<Category> giftPreferences;

    public ChildInputData(final Integer id, final String lastName,
                          final String firstName, final Integer age,
                          final Cities city, final Double niceScore,
                          final LinkedList<Category> giftPreferences) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.city = city;
        this.niceScore = niceScore;
        this.giftPreferences = giftPreferences;
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

    public Integer getAge() {
        return age;
    }

    public Cities getCity() {
        return city;
    }

    public Double getNiceScore() {
        return niceScore;
    }

    public LinkedList<Category> getGiftPreferences() {
        return giftPreferences;
    }

    @Override
    public String toString() {
        return "ChildInputData{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", age=" + age +
                ", city=" + city +
                ", niceScore=" + niceScore +
                ", giftPreferences=" + giftPreferences +
                '}';
    }
}
