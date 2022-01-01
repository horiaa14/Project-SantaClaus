package fileio;

import common.Constants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import utils.Utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The class reads and parses the data from the tests.
 */

public final class InputLoader {
    /**
     * The path to the input file
     */
    private final String inputPath;

    public InputLoader(final String inputPath) {
        this.inputPath = inputPath;
    }

    /**
     * Reading the general information: the number of rounds and the initial
     * budget of Santa Claus. The method constructs an object of type
     * SimulationInputData that encapsulates this information.
     * @return an object of type SimulationInputData that contains two fields.
     */
    public SimulationInputData parseSimulationData(final JSONObject jsonObject) {
        SimulationInputData generalData;
        generalData = new SimulationInputData(
                Integer.parseInt(jsonObject
                        .get(Constants.NUMBER_OF_YEARS).toString()),
                Double.parseDouble(jsonObject
                        .get(Constants.SANTA_BUDGET).toString())

        );
        return generalData;
    }

    /**
     * Reading information about children: The method builds an ArrayList
     * that stores all the information needed for each child. We will extract
     * the fields from the json file, call the constructor to form a
     * ChildInputData object and add it to the end of the ArrayList.
     *
     * @return an ArrayList containing information about children.
     */
    public ArrayList<ChildInputData> parseChildrenData(final JSONArray jsonChildren) {
        ArrayList<ChildInputData> children = new ArrayList<>();
        if (jsonChildren != null) {
            for (Object jsonIterator : jsonChildren) {
                children.add(new ChildInputData(
                        Integer.parseInt(((JSONObject) jsonIterator)
                                .get(Constants.ID).toString()),
                        (String) ((JSONObject) jsonIterator)
                                .get(Constants.LAST_NAME),
                        (String) ((JSONObject) jsonIterator)
                                .get(Constants.FIRST_NAME),
                        Integer.parseInt(((JSONObject) jsonIterator)
                                .get(Constants.AGE).toString()),
                        Utils.stringToCities((String) ((JSONObject) jsonIterator)
                                .get(Constants.CITY)),
                        Double.parseDouble(((JSONObject) jsonIterator)
                                .get(Constants.NICE_SCORE).toString()),
                        Utils.convertJSONArray((JSONArray) ((JSONObject) jsonIterator)
                                .get(Constants.GIFTS_PREFERENCES))
                ));
            }
        }

        return children;
    }

    /**
     * Reading information about gifts: This method builds an ArrayList
     * that encapsulates the name, price and category for each gift. We read
     * the three fields and call the constructor of the GiftInputData class.
     * Once this object is built, we will add it to the end of the ArrayList.
     *
     * @return an ArrayList containing information about gifts.
     */
    public ArrayList<GiftInputData> parseGiftsData(final JSONArray jsonGifts) {
        ArrayList<GiftInputData> gifts = new ArrayList<>();
        if (jsonGifts != null) {
            for (Object jsonIterator : jsonGifts) {
                gifts.add(new GiftInputData(
                        (String) ((JSONObject) jsonIterator)
                                .get(Constants.PRODUCT_NAME),
                        Double.parseDouble(((JSONObject) jsonIterator)
                                .get(Constants.PRICE).toString()),
                        Utils.stringToCategory((String) ((JSONObject) jsonIterator)
                                .get(Constants.CATEGORY))
                ));
            }
        }

        return gifts;
    }

    /**
     * In this method we want to build an ArrayList to store the information
     * about the changes in a year: id, niceScore and giftsPreferences for
     * a child. We take care to treat the case when niceScore is null.
     *
     * @return an ArrayList containing information about children's updates.
     */
    public ArrayList<ChildUpdateInputData> parseChildrenUpdates(
            final JSONArray jsonChildrenUpdates) {

        ArrayList<ChildUpdateInputData> updates = new ArrayList<>();
        if (jsonChildrenUpdates != null) {
            for (Object jsonIterator : jsonChildrenUpdates) {
                Double niceScore = null;
                if (((JSONObject) jsonIterator).get(Constants.NICE_SCORE) != null) {
                    niceScore = Double.parseDouble(((JSONObject) jsonIterator)
                            .get(Constants.NICE_SCORE).toString());
                }
                updates.add(new ChildUpdateInputData(
                        Integer.parseInt(((JSONObject) jsonIterator)
                                .get(Constants.ID).toString()), niceScore,
                        Utils.convertJSONArray((JSONArray) ((JSONObject) jsonIterator)
                                .get(Constants.GIFTS_PREFERENCES))
                ));
            }
        }

        return updates;
    }

    /**
     * In this method we parse the following information corresponding to a
     * change: the new budget of Santa Claus, the list of new gifts, the list of
     * new children, the list of updates. We read this information, call the
     * constructor of the UpdateInputData class and retain this new object
     * in the ArrayList for all annual changes.
     *
     * @return an ArrayList which contains the changes from all the years.
     */
    public ArrayList<UpdateInputData> parseAnnualChangesData(final JSONArray jsonUpdates) {
        ArrayList<UpdateInputData> annualChanges = new ArrayList<>();
        if (jsonUpdates != null) {
            for (Object jsonIterator : jsonUpdates) {
                Double newSantaBudget = Double.parseDouble(((JSONObject) jsonIterator)
                        .get(Constants.NEW_SANTA_BUDGET).toString());

                JSONArray jsonNewGifts =
                        (JSONArray) ((JSONObject) jsonIterator)
                                .get(Constants.NEW_GIFTS);

                JSONArray jsonNewChildren =
                        (JSONArray) ((JSONObject) jsonIterator)
                                .get(Constants.NEW_CHILDREN);

                JSONArray jsonChildrenUpdates =
                        (JSONArray) ((JSONObject) jsonIterator)
                                .get(Constants.CHILDREN_UPDATES);

                ArrayList<GiftInputData> newGifts =
                        parseGiftsData(jsonNewGifts);

                ArrayList<ChildInputData> newChildren =
                        parseChildrenData(jsonNewChildren);

                ArrayList<ChildUpdateInputData> updates =
                        parseChildrenUpdates(jsonChildrenUpdates);

                annualChanges.add(new UpdateInputData(newSantaBudget, newGifts,
                                 newChildren, updates));
            }
        }

        return annualChanges;

    }

    /**
     * In this function we build the object which will retain all the
     * information needed to implement the simulation. We will use the methods
     * defined above to parse the json file.
     *
     * @return an Input object that stores all the data needed for the
     * simulation.
     */
    public Input readData() {
        JSONParser jsonParser = new JSONParser();
        SimulationInputData generalData = null;
        ArrayList<ChildInputData> children = null;
        ArrayList<GiftInputData> gifts = null;
        ArrayList<UpdateInputData> annualChanges = null;

        try {
            JSONObject jsonObject = (JSONObject) jsonParser
                    .parse(new FileReader(inputPath));

            JSONObject initialData =
                    (JSONObject) jsonObject.get(Constants.INITIAL_DATA);

            JSONArray jsonChildren =
                    (JSONArray) initialData.get(Constants.CHILDREN);

            JSONArray jsonGifts =
                    (JSONArray) initialData.get(Constants.SANTA_GIFTS_LIST);

            JSONArray jsonUpdates =
                    (JSONArray) jsonObject.get(Constants.ANNUAL_CHANGES);

            generalData = parseSimulationData(jsonObject);
            children = parseChildrenData(jsonChildren);
            gifts = parseGiftsData(jsonGifts);
            annualChanges = parseAnnualChangesData(jsonUpdates);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        return new Input(generalData, children, gifts, annualChanges);
    }
}
