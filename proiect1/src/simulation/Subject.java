package simulation;

import fileio.UpdateInputData;

public interface Subject {
    void addObserver(Observer o);
    void notifyObservers(UpdateInputData annualChange);
}
