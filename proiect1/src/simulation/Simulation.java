package simulation;

import children.AverageScoreCalculator;
import children.AverageScoreImpl;
import children.Child;
import database.ChildrenDatabase;
import database.GiftsDatabase;
import enums.Category;
import fileio.AnnualChildrenOutput;
import fileio.ChildListOutput;
import fileio.Input;
import fileio.UpdateInputData;
import fileio.Writer;
import gift.Gift;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * The class that contains the data and methods needed to implement the
 * simulation
 */

public final class Simulation implements Subject {
    private final Integer numberOfYears;
    private final ArrayList<UpdateInputData> annualChanges;
    private final ArrayList<Observer> observers;
    private Double santaBudget;

    public Simulation(final Integer numberOfYears, final Double santaBudget,
                      final ArrayList<UpdateInputData> annualChanges) {
        this.numberOfYears = numberOfYears;
        this.santaBudget = santaBudget;
        this.annualChanges = annualChanges;
        this.observers = new ArrayList<>();
    }

    /**
     * In this method we add an observer to the list of observers.
     * @param obs is the observer that will be added
     */
    public void addObserver(final Observer obs) {
        observers.add(obs);
    }

    /**
     * This is the method that notifies the observers at the end of a round.
     * For each observer we will call the update method.
     * @param annualChange is an object that contains information about changes
     */
    public void notifyObservers(final UpdateInputData annualChange) {
        observers.forEach(o -> o.update(annualChange));
    }

    /**
     * We will delete the contents of the databases before starting the
     * simulation.
     * @param childrenDatabase is an object that contains a list of children
     * @param giftsDatabase is an object that contains a list of gifts
     */
    public void clearDatabases(final ChildrenDatabase childrenDatabase,
                               final GiftsDatabase giftsDatabase) {

        childrenDatabase.getChild().clear();
        giftsDatabase.getGift().clear();
    }

    /**
     * This method is used to prepare the simulation. We will delete the
     * current contents of the databases, populate them with the information
     * read and add observers.
     * @param input contains information about the initial list of children
     *              and the list of gifts
     * @param childrenDatabase represents the database with children
     * @param giftsDatabase is the database with gifts
     */
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

    /**
     * From this method we will call the function that performs the simulation
     * (simulateRounds). Before starting the simulation we will call the
     * function that initializes the databases.
     * @param input contains information about child and gift databases.
     * @param outFile is an object of type File, its name corresponds to the
     *                file in which the results are displayed
     */
    public void executeSimulation(final Input input, final File outFile)
                                  throws IOException {
        ChildrenDatabase childrenDatabase = ChildrenDatabase.getUniqueInstance();
        GiftsDatabase giftsDatabase = GiftsDatabase.getUniqueInstance();
        initSimulation(input, childrenDatabase, giftsDatabase);
        simulateRounds(childrenDatabase, giftsDatabase, outFile);
    }

    /**
     * The method calculates the average score for each child in the list.
     * All objects in the list contain a function named "accept". Depending
     * on the type of object, the corresponding function is called. To calculate
     * these scores we will declare a computer that contains calculation
     * methods for each type of object in the list.This function exploits the
     * use of the Visitor design pattern and, implicitly, the polymorphism.
     * @param childrenDtb contains a list of children
     */
    public void calculateAverageScore(final ChildrenDatabase childrenDtb) {
        AverageScoreCalculator calculator = new AverageScoreImpl();
        ArrayList<Child> children = childrenDtb.getChild();
        for (Child currChild : children) {
            currChild.setAverageScore(currChild.accept(calculator));
        }
    }

    /**
     * The method calculates the sum of all children's scores, in the order
     * given by id.
     * @param children is the list which contains the children
     * @return an object of type Double that represents the sum
     */
    public Double getSumOfAverageScores(final ArrayList<Child> children) {
        double sum = 0;
        for (Child currChild : children) {
            sum += currChild.getAverageScore();
        }
        return sum;
    }

    /**
     * In this method we calculate the budget allocated for each child.
     * @param children is the list which contains the children
     */
    public void calculateBudgetForChildren(final ArrayList<Child> children) {
        Double budgetUnit = santaBudget / getSumOfAverageScores(children);
        for (Child currChild : children) {
            currChild.setAssignedBudget(currChild.getAverageScore() * budgetUnit);
        }
    }

    /**
     * In this method we will go through the list of gifts and check if there is
     * a gift from the category passed as a parameter that fits in the budget.
     * If there are several options, we will choose the gift with the lowest
     * price.
     * @param category represents the category from which we are looking for
     *                 the gift
     * @param gifts is a list of gifts
     * @return an object of type Gift or null if we can't find a gift
     */
    public Gift selectGift(final Category category,
                           final ArrayList<Gift> gifts, final Double budget) {

        Gift gift = null;
        for (Gift currGift : gifts) {
            if (currGift.getCategory() == category
                    && currGift.getPrice() <= budget) {
                if (gift == null) {
                    gift = currGift;
                } else if (currGift.getPrice() < gift.getPrice()) {
                        gift = currGift;
                }
            }
        }
        return gift;
    }

    /**
     * For each child we will go through the list of preferences, and we will
     * try to offer a gift from each category, in the order in which they appear
     * in the list. We will use the method defined above to select a gift
     * (selectGift).
     * @param children is a list of children
     * @param gifts represents a list of gifts
     */
    public void distributeGifts(final ArrayList<Child> children,
                                final ArrayList<Gift> gifts) {
        for (Child currChild : children) {
            double budget = currChild.getAssignedBudget();
            LinkedList<Category> giftsPreferences =
                    currChild.getGiftPreferences();

            ArrayList<Gift> receivedGifts = currChild.getReceivedGifts();
            receivedGifts.clear();

            /*We go through the categories in order and try to give a gift
            from each one.*/
            for (var currentCategory : giftsPreferences) {
                Gift gift = selectGift(currentCategory, gifts, budget);
                if (gift != null) {
                    budget -= gift.getPrice();
                    receivedGifts.add(gift);
                }
            }
        }
    }

    /**
     * In this method we complete the list of annual information which we will
     * display at the end of the simulation.
     * @param annualChildrenList is the list of annual information
     * @param children represents the list of children who received gifts
     *                 this year
     */
    void updateAnnualChildrenList(final AnnualChildrenOutput annualChildrenList,
                                  final ArrayList<Child> children) {
        ChildListOutput list = new ChildListOutput();
        list.completeList(children);
        annualChildrenList.addChildrenList(list);
    }

    /**
     * This method is the core of the simulation. In it, we use the methods
     * defined above to perform all operations in the simulation.
     * @param childrenDtb is the database that contains the children
     * @param giftsDtb represents the database that contains the gifts
     * @param outFile is an object of type File, its name corresponds to the
     *                file in which the results are displayed
     */
    public void simulateRounds(final ChildrenDatabase childrenDtb,
                               final GiftsDatabase giftsDtb,
                               final File outFile) throws IOException {

        Writer writer = new Writer();
        AnnualChildrenOutput annualChildrenList = new AnnualChildrenOutput();

        for (int currRound = 0; currRound <= numberOfYears; ++currRound) {
            calculateAverageScore(childrenDtb);
            calculateBudgetForChildren(childrenDtb.getChild());
            distributeGifts(childrenDtb.getChild(), giftsDtb.getGift());

            /*We add the children who received gifts in this round to the list
            that contains information about each round*/
           updateAnnualChildrenList(annualChildrenList, childrenDtb.getChild());

            if (currRound < numberOfYears) {
                santaBudget = annualChanges.get(currRound).getNewSantaBudget();
                notifyObservers(annualChanges.get(currRound));
            }
        }

        writer.printJSON(outFile, annualChildrenList);
    }

    /**
     * This method removes the duplicates from the received list.
     * @param list represents the list from which we remove the duplicate
     *             items.
     */
    public static void removeDuplicates(final LinkedList<Category> list) {
        LinkedList<Category> uniqueElements = new LinkedList<>();
        for (Category currCategory : list) {
            if (!uniqueElements.contains(currCategory)) {
                uniqueElements.add(currCategory);
            }
        }

        list.clear();
        list.addAll(uniqueElements);
    }

    public void setSantaBudget(final Double santaBudget) {
        this.santaBudget = santaBudget;
    }
}
