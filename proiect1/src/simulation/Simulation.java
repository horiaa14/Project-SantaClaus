package simulation;

import children.AverageScoreCalculator;
import children.AverageScoreImpl;
import children.Child;
import database.ChildrenDatabase;
import database.GiftsDatabase;
import enums.Category;
import fileio.Input;
import fileio.UpdateInputData;
import gift.Gift;

import java.util.ArrayList;
import java.util.LinkedList;

public final class Simulation implements Subject {
    private final Integer numberOfYears;
    private final ArrayList<UpdateInputData> annualChanges;
    private final ArrayList<Observer> observers;
    private Double santaBudget;

    public Simulation(final Integer numberOfYears, Double santaBudget,
                      final ArrayList<UpdateInputData> annualChanges) {
        this.numberOfYears = numberOfYears;
        this.santaBudget = santaBudget;
        this.annualChanges = annualChanges;
        this.observers = new ArrayList<>();
    }

    public void addObserver(final Observer obs) {
        observers.add(obs);
    }

    public void notifyObservers(final UpdateInputData annualChange) {
        observers.forEach(o -> o.update(annualChange));
    }

    public void clearDatabases(final ChildrenDatabase childrenDatabase,
                               final GiftsDatabase giftsDatabase) {

        childrenDatabase.getChild().clear();
        giftsDatabase.getGift().clear();
    }

    public void initSimulation(final Input input,
                               final ChildrenDatabase childrenDatabase,
                               final GiftsDatabase giftsDatabase) {

        clearDatabases(childrenDatabase, giftsDatabase);
        childrenDatabase.buildChildrenDatabase(input.getChildrenData());
        giftsDatabase.buildGiftsDatabase(input.getGiftsData());
        observers.clear();
        addObserver(childrenDatabase);
        addObserver(giftsDatabase);
    }

    public void executeSimulation(final Input input, final String outputFile) {
        ChildrenDatabase childrenDatabase = ChildrenDatabase.getUniqueInstance();
        GiftsDatabase giftsDatabase = GiftsDatabase.getUniqueInstance();
        initSimulation(input, childrenDatabase, giftsDatabase);
        simulateRounds(childrenDatabase, giftsDatabase, outputFile);
    }

    public void calculateAverageScore(final ChildrenDatabase childrenDtb) {
        AverageScoreCalculator calculator = new AverageScoreImpl();
        ArrayList<Child> children = childrenDtb.getChild();
        for (Child currChild : children) {
            currChild.setAverageScore(currChild.accept(calculator));
        }
    }

    public Double getSumOfAverageScores(final ArrayList<Child> children) {
        double sum = 0;
        for (Child currChild : children) {
            sum += currChild.getAverageScore();
        }
        return sum;
    }

    public void calculateBudgetForChildren(final ArrayList<Child> children) {
        Double budgetUnit = santaBudget / getSumOfAverageScores(children);
        for (Child currChild : children) {
            currChild.setAssignedBudget(currChild.getAverageScore() * budgetUnit);
        }
    }

    public Gift selectGift(final Category category,
                           final ArrayList<Gift> gifts, final Double budget) {

        Gift gift = null;
        for (Gift currGift : gifts) {
            if (currGift.getCategory() == category &&
                    currGift.getPrice() <= budget) {
                if (gift == null) {
                    gift = currGift;
                } else if (currGift.getPrice() < gift.getPrice()) {
                        gift = currGift;
                }
            }
        }
        return gift;
    }

    public void distributeGifts(final ArrayList<Child> children,
                                final ArrayList<Gift> gifts) {
        for (Child currChild : children) {
            double budget = currChild.getAssignedBudget();
            LinkedList<Category> giftsPreferences =
                    currChild.getGiftPreferences();

            ArrayList<Gift> receivedGifts = currChild.getReceivedGifts();
            receivedGifts.clear();
            for (var currentCategory : giftsPreferences) {
                Gift gift = selectGift(currentCategory, gifts, budget);
                if (gift != null) {
                    budget -= gift.getPrice();
                    receivedGifts.add(gift);
                }
            }
        }
    }

    public void simulateRounds(final ChildrenDatabase childrenDtb,
                               final GiftsDatabase giftsDtb,
                               final String outputFile) {

        for (int currRound = 0; currRound <= numberOfYears; ++currRound) {
            calculateAverageScore(childrenDtb);
            calculateBudgetForChildren(childrenDtb.getChild());
            distributeGifts(childrenDtb.getChild(), giftsDtb.getGift());
            // Write
            System.out.println(childrenDtb);
            if (currRound < numberOfYears) {
                santaBudget = annualChanges.get(currRound).getNewSantaBudget();
                notifyObservers(annualChanges.get(currRound));
            }
        }
    }

    public void setSantaBudget(Double santaBudget) {
        this.santaBudget = santaBudget;
    }
}
