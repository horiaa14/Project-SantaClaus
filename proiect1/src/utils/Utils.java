package utils;

import enums.Category;
import enums.Cities;
import org.json.simple.JSONArray;

import java.util.LinkedList;

public final class Utils {
    private Utils() {}

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
