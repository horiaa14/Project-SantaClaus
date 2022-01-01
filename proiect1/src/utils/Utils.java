package utils;

import enums.Category;
import enums.Cities;
import org.json.simple.JSONArray;

import java.util.LinkedList;

/**
 * The class contains methods that helps with parsing
 */

public final class Utils {
    private Utils() {

    }

    /**
     * The method transforms a String into an enum object (Cities)
     */
    public static Cities stringToCities(final String strCities) {
        return switch (strCities) {
            case "Bucuresti" -> Cities.BUCURESTI;
            case "Constanta" -> Cities.CONSTANTA;
            case "Buzau" -> Cities.BUZAU;
            case "Timisoara" -> Cities.TIMISOARA;
            case "Cluj-Napoca" -> Cities.CLUJ;
            case "Iasi" -> Cities.IASI;
            case "Craiova" -> Cities.CRAIOVA;
            case "Brasov" -> Cities.BRASOV;
            case "Braila" -> Cities.BRAILA;
            case "Oradea" -> Cities.ORADEA;
            default -> null;
        };
    }

    /**
     * The method transforms a String into an enum object (Category)
     */
    public static Category stringToCategory(final String strCategory) {
        return switch (strCategory) {
            case "Board Games" -> Category.BOARD_GAMES;
            case "Books" -> Category.BOOKS;
            case "Clothes" -> Category.CLOTHES;
            case "Sweets" -> Category.SWEETS;
            case "Technology" -> Category.TECHNOLOGY;
            case "Toys" -> Category.TOYS;
            default -> null;
        };
    }

    /**
     * This method transforms an array of JSON's into a LinkedList of type
     * Category
     * @param array of JSONs
     * @return a LinkedList of type Category (enum)
     */
    public static LinkedList<Category> convertJSONArray(final JSONArray array) {
        if (array == null) {
            return null;
        }

        LinkedList<Category> result = new LinkedList<>();
        for (Object elem : array) {
            result.add(Utils.stringToCategory((String) elem));
        }

        return result;
    }
}
