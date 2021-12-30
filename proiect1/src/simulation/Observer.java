package simulation;

import fileio.UpdateInputData;

public interface Observer {
    void update(final UpdateInputData annualChange);
}
