package at.aau.core;

import org.json.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class DataParser {

    // TODO: REPLACE ABSOLUTE PATHS WITH RELATIVE ANDROID PATHS!

    // Get:



    public static List<Country> getCountries() {
        // Open JSON file:
        FileReader reader = null;
        try {
            reader = new FileReader("C:/Users/daneg/Downloads/Projects/SE2-Risiko/core/src/main/java/at/aau/core/Countries.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Parse JSON file:
        JSONTokener tokener = new JSONTokener(reader);
        JSONObject object = new JSONObject(tokener);
        JSONArray data = object.getJSONArray("countries");

        // Convert into Country[]:
        List<Country> countries = new LinkedList<>();
        for (int i = 0; i < data.length(); ++i) {
            Country country = new Country(data.getJSONObject(i).getString("name"));

            JSONArray neighbors = data.getJSONObject(i).getJSONArray("neighbors");
            for (int j = 0; j < neighbors.length(); ++j) {
                System.out.println(neighbors.getString(j));
            }

            countries.add(country);
        }

        return countries;
    }

    public static List<Card> getCards() {
        // Open JSON file:
        FileReader reader = null;
        try {
            reader = new FileReader("C:/Users/daneg/Downloads/Projects/SE2-Risiko/core/src/main/java/at/aau/core/Cards.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Parse JSON file:
        JSONTokener tokener = new JSONTokener(reader);
        JSONObject object = new JSONObject(tokener);
        JSONArray data = object.getJSONArray("cards");

        // Convert into Card[]:
        LinkedList<Card> cards = new LinkedList<Card>();
        for (int i = 0; i < data.length(); ++i) {
            Card card = new Card(data.getJSONObject(i).getString("name"), data.getJSONObject(i).getString("type"), false, false);

            cards.add(card);
        }

        return cards;
    }

    // Print:

    public static void printCountries() {
        FileReader reader = null;
        try {
            reader = new FileReader("C:/Users/daneg/Downloads/Projects/SE2-Risiko/core/src/main/java/at/aau/core/Countries.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JSONTokener tokener = new JSONTokener(reader);
        JSONObject object = new JSONObject(tokener);

        JSONArray countries = object.getJSONArray("countries");

        for (int i = 0; i < countries.length(); ++i) {
            System.out.println(countries.getJSONObject(i).getString("name") + ":");
            JSONArray neighbors = countries.getJSONObject(i).getJSONArray("neighbors");
            for (int j = 0; j < neighbors.length(); ++j) {
                System.out.println(neighbors.getString(j));
            }
            System.out.println();
        }
    }

    // \"neighbors\"\: \[\s*(\"\w*\"\,?\s*)*] - for replacing country with card data
    public static void printCards() {
        FileReader reader = null;
        try {
            reader = new FileReader("C:/Users/daneg/Downloads/Projects/SE2-Risiko/core/src/main/java/at/aau/core/Cards.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JSONTokener tokener = new JSONTokener(reader);
        JSONObject object = new JSONObject(tokener);

        JSONArray cards = object.getJSONArray("cards");

        for (int i = 0; i < cards.length(); ++i) {
            System.out.println(cards.getJSONObject(i).getString("name") + ":");
            System.out.println(cards.getJSONObject(i).getString("type"));
            System.out.println();
        }
    }

}

/*
I/CentralAmerica: 0.453 : 0.138
I/Venezuela: 0.549 : 0.227
I/China: 0.36 : 0.795
I/Ethiopia: 0.594 : 0.584
I/MiddleEast: 0.36 : 0.604
I/Quebec: 0.208 : 0.254
I/Siam: 0.498 : 0.795
I/Ukraine: 0.235 : 0.53
I/WestEurope: 0.26 : 0.434
I/Siberia: 0.168 : 0.682
I/Ontario: 0.168 : 0.162
I/Scandinavia: 0.112 : 0.515
I/EastAustralia: 0.752 : 0.916
I/India: 0.402 : 0.707
I/NorthAfrica: 0.472 : 0.434
I/Yakutsk: 0.168 : 0.828
I/SouthAfrica: 0.722 : 0.532
I/WestCoast: 0.304 : 0.11
I/Argentina: 0.848 : 0.254
I/Greenland: 0.09 : 0.374
I/Mongolia: 0.264 : 0.733
I/NewGuinea: 0.594 : 0.943
I/Congo: 0.568 : 0.515
I/Peru: 0.722 : 0.227
I/Indonesia: 0.594 : 0.847
I/WestAustralia: 0.752 : 0.828
I/Brazil: 0.645 : 0.293
I/Alaska: 0.136 : 0.061
I/EastCoast: 0.328 : 0.189
I/Egypt: 0.424 : 0.532
I/Ural: 0.208 : 0.604
 */