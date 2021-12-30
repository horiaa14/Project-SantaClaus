package main;

import checker.Checker;
import common.Constants;
import fileio.Input;
import fileio.InputLoader;
import simulation.Simulation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        ///constructor for checkstyle
    }

    public static void deleteFiles(final File[] directory) {
        if (directory != null) {
            for (File file : directory) {
                if (!file.delete()) {
                    System.out.println("The file was not deleted!");
                }
            }
        }
    }

    /**
     * This method is used to call the checker which calculates the score
     * @param args
     *          the arguments used to call the main method
     */
    public static void main(final String[] args) throws IOException {
        File inputDirectory = new File(Constants.TESTS_PATH);
        Path path = Paths.get(Constants.RESULT_PATH);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        File outputDirectory = new File(Constants.RESULT_PATH);
        deleteFiles(outputDirectory.listFiles());
        for (File file : Objects.requireNonNull(inputDirectory.listFiles())) {
            String filePath = Constants.OUTPUT_PATH + file.getName().substring
                                (Constants.START_INDEX);

            File out = new File(filePath);
            boolean isCreated = out.createNewFile();

            if (isCreated) {
                action(file.getAbsolutePath(), filePath);
            }
        }

        Checker.calculateScore();
    }

    public static void action(final String inputFile, final String outputFile) {
        InputLoader inputLoader = new InputLoader(inputFile);
        Input input = inputLoader.readData();

        Simulation simulation =
                new Simulation(input.getSimulationData().getNumberOfYears(),
                        input.getSimulationData().getSantaBudget(),
                        input.getAnnualChangesData());

        simulation.executeSimulation(input, outputFile);
    }
}
