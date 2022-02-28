package fileio;

import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;

/**
 * This class is used to write information about simulation in output files.
 */

public class Writer {

    /**
     * The method displays the result of the entire simulation in the output
     * file.
     * @param outFile is an object of type File, its name corresponds to the
     *                file in which the results are displayed.
     *
     * @param obj represents an object that contains information about
     *            simulating all rounds.
     */
    public final void printJSON(final File outFile, final AnnualChildrenOutput obj)
                          throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter();
        prettyPrinter.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);

        objectMapper.writeValue(outFile, obj);
    }
}
