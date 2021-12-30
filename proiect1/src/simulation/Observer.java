package simulation;

import fileio.UpdateInputData;

public interface Observer {
    void update(UpdateInputData annualChange);
}
