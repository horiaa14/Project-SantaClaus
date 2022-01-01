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

    /**
     * This function is used to delete all files in the given directory.
     */
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
     * In this method we will go through the list of input files and generate
     * the names of the output files based of them.
     * Also, in this method we call the checker which calculates the score.
     */
    public static void main(final String[] args) throws IOException {
        File inputDirectory = new File(Constants.TESTS_PATH);
        /*First we will create the directory in which we will write the
        test results.*/
        Path path = Paths.get(Constants.RESULT_PATH);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        File outputDirectory = new File(Constants.RESULT_PATH);
        deleteFiles(outputDirectory.listFiles());
        /*We iterate through the input files and generate the name of the
        output files*/
        for (File file : Objects.requireNonNull(inputDirectory.listFiles())) {
            String filePath = Constants.OUTPUT_PATH + file.getName().substring(
                              Constants.START_INDEX);

            File out = new File(filePath);
            boolean isCreated = out.createNewFile();

            if (isCreated) {
                action(file.getAbsolutePath(), out);
            }
        }

        Checker.calculateScore();
    }

    /**
     * This method will be called at each test. Here we will read the data
     * from the input file, we will instantiate an object of type Simulation,
     * and we will call the method for running the simulation.
     * @param inputFile is the name of the file from which we read the data
     * @param outFile  represents a reference to the file in which we write
     *                 the simulation result.
     */
    public static void action(final String inputFile, final File outFile)
                              throws IOException {
        InputLoader inputLoader = new InputLoader(inputFile);
        Input input = inputLoader.readData();

        Simulation simulation =
                new Simulation(input.getSimulationData().getNumberOfYears(),
                        input.getSimulationData().getSantaBudget(),
                        input.getAnnualChangesData());

        simulation.executeSimulation(input, outFile);
    }
}
