package children;

public interface AverageScoreCalculator {
    /**
     * This method calculates the average score for a Baby object
     * @return a double value that represents the average score
     */
    Double getAverageScore(Baby baby);

    /**
     * This method calculates the average score for a Kid object
     * @return a double value that represents the average score
     */
    Double getAverageScore(Kid kid);

    /**
     * This method calculates the average score for a Teen object
     * @return a double value that represents the average score
     */
    Double getAverageScore(Teen teen);
}
