package kata;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

class DataService {

    private static final String USER_URL = "https://randomuser.me/api/?inc=gender,name,email,location,dob&results=5&seed=a1b25cd956e2038h";

    public ArrayList<String[]> getDataFromUrl() throws IOException {
        // Parse URL content
        String url = USER_URL;
        String command = "curl -X GET " + url;
        ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
        Process process = processBuilder.start();
        InputStream processInputStream = process.getInputStream();
        Scanner webProvider = new Scanner(processInputStream);
        String result = "";
        while (webProvider.hasNextLine()) {
            result += webProvider.nextLine();
        }
        webProvider.close();
        JSONObject jsonObject = new JSONObject(result);
        JSONArray results = jsonObject.getJSONArray("results");

        BigInteger j = new BigInteger("100000000000");
        ArrayList<String[]> usersDataFromWeb = new ArrayList<>();
        for (int i = 0; i < results.length(); i++) {
            j = j.add(new BigInteger("1"));
            usersDataFromWeb.add(new String[] {
                    j.toString(), // id
                    results.getJSONObject(i).getString("gender"),
                    results.getJSONObject(i).getJSONObject("name").getString("first") + " " + results
                            .getJSONObject(i)
                            .getJSONObject("name")
                            .getString("last"),
                    results.getJSONObject(i).getJSONObject("location").getString("country"),
                    String.valueOf(results.getJSONObject(i).getJSONObject("location").get("postcode")),
                    results.getJSONObject(i).getString("email"),
                    String.valueOf(results.getJSONObject(i).getJSONObject("dob").getString("date")) // birhtday
            });
        }
        return usersDataFromWeb;
    }

    // TODO: could we parse the String[] into an Object, User class or something?
    public ArrayList<String[]> getDataFromCsv(String csvFileName) {
        // Parse CSV file
        ArrayList<String[]> result = new ArrayList<>();
        Scanner csvFile = new Scanner(Thread.currentThread().getContextClassLoader().getResourceAsStream(csvFileName));
        while (csvFile.hasNextLine()) {
            String line = csvFile.nextLine();
            // fields: ID, gender, Name ,country, postcode, email, Birthdate
            String[] attributes = line.split(",");
            if (attributes.length == 0) {
                continue;
            }
            result.add(attributes);
        }
        result.removeFirst(); // Remove header column

        return result;
    }
}
