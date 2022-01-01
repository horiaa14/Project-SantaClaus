package simulation;

import fileio.UpdateInputData;

/**
 * This interface will be implemented by the class that represents the subject
 * in the Observer design pattern: Simulation.
 */

public interface Subject {
    /**
     * This method adds an observer to the list of observers.
     * @param o represents the observer which will be added
     */
    void addObserver(Observer o);

    /**
     * In this method we will go through the list of observers, and we will
     * call the update method for each one.
     * @param annualChange is an object that contains information about changes
     */
    void notifyObservers(UpdateInputData annualChange);
}
