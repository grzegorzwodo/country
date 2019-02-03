package pl.sdacademy;

import com.google.gson.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class Country {

    public static void main(String[] args) throws FileNotFoundException {
        JsonParser jsonParser = new JsonParser();
        Reader reader = new FileReader("poland.json");
        JsonElement jsonElement = jsonParser.parse(reader);
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        JsonObject countryRoot = jsonArray.get(0).getAsJsonObject();
        String population = countryRoot.getAsJsonPrimitive("population").getAsString();
        JsonArray borders = countryRoot.getAsJsonArray("borders");
        //1
        System.out.println(population);
        //2
        for (JsonElement border : borders) {
            System.out.println("Polska graniczy z " + border);
        }
        //3
        JsonObject translations = countryRoot.getAsJsonObject("translations");
        JsonPrimitive polandIt = translations.getAsJsonPrimitive("it");
        System.out.println("Polska po włosku to: " + polandIt.getAsString());
    }
}
