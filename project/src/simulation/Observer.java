package simulation;

import fileio.UpdateInputData;

/**
 * This interface will be implemented by classes that are observers:
 * ChildrenDatabase and GiftsDatabase.
 */

public interface Observer {
    /**
     * This method will be overridden in all classes that fulfill the role
     * of observer. At the end of a round, observers will be notified to be
     * updated, because the status of the subject will change (move on to the
     * next round).
     * @param annualChange contains information about the changes (new children,
     *                     new gifts, updating data about existing children)
     */
    void update(UpdateInputData annualChange);
}
