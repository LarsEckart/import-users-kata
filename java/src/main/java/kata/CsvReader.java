package kata;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

class CsvReader {

  static ArrayList<User> readCsv() {
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
            Birthdate.fromIsoString(attributes[6]),
            attributes[3],
            attributes[4],
            attributes[5]));
      }
    }
    return users;
  }

}
