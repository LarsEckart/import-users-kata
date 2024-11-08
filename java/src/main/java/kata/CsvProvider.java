package kata;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class CsvProvider implements DataProvider {

  private String csvFileName;

  public CsvProvider(String csvFileName) {
    this.csvFileName = csvFileName;
  }

  // TODO: could we parse the String[] into an Object, User class or something?
  @Override
  public List<String[]> getData() {
    // Parse CSV file
    ArrayList<String[]> result = new ArrayList<>();
    Scanner csvFile = new Scanner(
        Thread.currentThread().getContextClassLoader().getResourceAsStream(csvFileName));
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
