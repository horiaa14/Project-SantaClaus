package simulation;

import fileio.UpdateInputData;

public interface Subject {
    void addObserver(final Observer o);
    void notifyObservers(final UpdateInputData annualChange);
}
