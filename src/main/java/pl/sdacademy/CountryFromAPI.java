package pl.sdacademy;

import com.google.gson.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CountryFromAPI {

    public static void main(String[] args) throws IOException {
        JsonParser jsonParser = new JsonParser();
        System.out.println("Podaj nazwe kraju w jezyku ang:");
        Scanner scanner = new Scanner(System.in);
        String country = scanner.nextLine();
        //trzeba pobrac json z servera o danym kraju
        // https://restcountries.eu/rest/v2/name/{podany kraj z konsoli}
        URL url = new URL("https://restcountries.eu/rest/v2/name/"+country);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        //utworzenie obiektu reader tak aby przekazac go do parsera
        InputStreamReader inputStreamReader = new InputStreamReader(con.getInputStream());

        JsonElement jsonElement = jsonParser.parse(inputStreamReader);
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        JsonObject countryRoot = jsonArray.get(0).getAsJsonObject();
        String population = countryRoot.getAsJsonPrimitive("population").getAsString();
        JsonArray borders = countryRoot.getAsJsonArray("borders");
        //1
        System.out.println(population);
        //2
        for (JsonElement border : borders) {
            System.out.println(country + " graniczy z " + border);
        }
        //3
        JsonObject translations = countryRoot.getAsJsonObject("translations");
        JsonPrimitive polandIt = translations.getAsJsonPrimitive("it");
        System.out.println("Polska po włosku to: " + polandIt.getAsString());

    }
}
