package kata;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONObject;

public class Main {

  private static final String USER_URL = "https://randomuser.me/api/?inc=gender,name,email,location,dob&results=5&seed=a9b25cd955e2037h";

  public static void main(String[] args) throws Exception {
    doStuff(Main::output);
    doStuff(Main::outputToTextFile);
  }

  private static void doStuff(Consumer<ArrayList<User>> output) throws IOException {
    ArrayList<User> users = new ArrayList<>();

    users.addAll(readCsv());
    users.addAll(readInternet());

    output.accept(users);
  }

  private static ArrayList<User> readInternet() throws IOException {
    ArrayList<User> users = new ArrayList<>();
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
    for (int i = 0; i < results.length(); i++) {
      j = j.add(new BigInteger("1"));
      users.add(new User(
          Long.parseLong(j.toString()), // id
          results.getJSONObject(i).getJSONObject("name").getString("first") + " " + results
              .getJSONObject(i)
              .getJSONObject("name")
              .getString("last"),
          parseToLocalDate(results.getJSONObject(i).getJSONObject("dob").getString("date")),
          results.getJSONObject(i).getJSONObject("location").getString("country"),
          "zip",
          results.getJSONObject(i).getString("email"))
      );
    }
    return users;
  }

  private static ArrayList<User> readCsv() {
    // Parse CSV file
    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    InputStream is = classloader.getResourceAsStream("users.csv");
    ArrayList<User> users = new ArrayList<>();
    Scanner csvFile = new Scanner(is);
    while (csvFile.hasNextLine()) {
      String line = csvFile.nextLine();
      // fields: ID, gender, Name ,country, postcode, email, Birthdate
      String[] attributes = line.split(",");
      if (attributes.length == 0) {
        continue;
      }
      if (!attributes[0].equals("id")) {
        users.add(new User(
            Long.parseLong(attributes[0]),
            attributes[2],
            parseToLocalDate(attributes[6]),
            attributes[3],
            attributes[4],
            attributes[5]));
      }
    }
    return users;
  }

  private static LocalDate parseToLocalDate(String attribute) {
    return ZonedDateTime.parse(attribute).toLocalDate();
  }

  private static void output(ArrayList<User> users) {
    System.out.println(
        "*****************************************************************************************************************************************");
    System.out.println(String.format(
        "* %-12s * %-12s * %-10s * %-20s * %-24s * %-40s *",
        "ID",
        "COUNTRY",
        "ZIP",
        "NAME",
        "DOB",
        "EMAIL"));
    System.out.println(
        "*****************************************************************************************************************************************");
    for (var user : users) {
      System.out.println(String.format(
          "* %-12s * %-12s * %-10s * %-20s * %-24s * %-40s *",
          user.id(),
          user.country(),
          user.zip(),
          user.name(),
          user.dob(),
          user.email()));
    }
    System.out.println(
        "*****************************************************************************************************************************************");
    System.out.println(users.size() + " users in total!");
  }

  private static void outputToTextFile(ArrayList<User> users) {
    try {
      Files.writeString(Path.of("output.txt"), users.stream().map(User::toString).collect(
          Collectors.joining("\n")));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
