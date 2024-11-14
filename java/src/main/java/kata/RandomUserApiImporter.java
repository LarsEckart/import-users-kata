package kata;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

class RandomUserApiImporter implements Importer {

  public static final String USER_URL = "https://randomuser.me/api/?inc=gender,name,email,location,dob&results=5&seed=a9b25cd955e2037h";
  // Parse URL content
  public String url;

  public RandomUserApiImporter(String url) {
    this.url = url;
  }

  @Override
  public ArrayList<User> importUsers() {
    ArrayList<User> users = new ArrayList<>();
    String url = this.url;
    String command = "curl -X GET " + url;
    ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
    Process process = null;
    try {
      process = processBuilder.start();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
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
    for (int i = 0; i < results.length(); i++) {
      j = j.add(new BigInteger("1"));
      users.add(new User(
          Long.parseLong(j.toString()), // id
          results.getJSONObject(i).getJSONObject("name").getString("first") + " " + results
              .getJSONObject(i)
              .getJSONObject("name")
              .getString("last"),
          Birthdate.fromIsoString(
              results.getJSONObject(i).getJSONObject("dob").getString("date")),
          results.getJSONObject(i).getJSONObject("location").getString("country"),
          String.valueOf(results.getJSONObject(i).getJSONObject("location").get("postcode")),
          results.getJSONObject(i).getString("email"))
      );
    }
    return users;
  }
}
