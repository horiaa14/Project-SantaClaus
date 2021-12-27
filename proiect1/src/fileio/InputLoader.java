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

public final class InputLoader {
    /**
     * The path to the input file
     */

    private final String inputPath;

    public InputLoader(final String inputPath) {
        this.inputPath = inputPath;
    }

    public String getInputPath() {
        return inputPath;
    }

    /**
     * This method reads all the data from the json file
     *
     * @return an Input object
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

    public ArrayList<ChildUpdateInputData> parseChildrenUpdates(
            final JSONArray jsonChildrenUpdates) {

        ArrayList<ChildUpdateInputData> updates = new ArrayList<>();
        if (jsonChildrenUpdates != null) {
            for (Object jsonIterator : jsonChildrenUpdates) {
                updates.add(new ChildUpdateInputData(
                        Integer.parseInt(((JSONObject) jsonIterator)
                                .get(Constants.ID).toString()),
                        Double.parseDouble(((JSONObject) jsonIterator)
                                .get(Constants.NICE_SCORE).toString()),
                        Utils.convertJSONArray((JSONArray) ((JSONObject) jsonIterator)
                                .get(Constants.GIFTS_PREFERENCES))
                ));
            }
        }

        return updates;
    }

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
