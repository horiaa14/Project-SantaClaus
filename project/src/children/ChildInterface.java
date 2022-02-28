package children;

public interface ChildInterface {
    /**
     * The method will be overwritten in both the Child class and the classes
     * derived from it. Depending on the type of object, the corresponding
     * function for calculating the average score will be called.
     * @param calculator an object that contains all the methods for calculating
     *                   the average score.
     * @return a Double value that represents the average score.
     */
    Double accept(AverageScoreCalculator calculator);
}
