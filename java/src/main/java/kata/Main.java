package kata;

import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    testable(
        List.of(new CsvProvider("users.csv"), new SeededRandomUserDataProvider(), new SqLiteDataProvider("users-source.db")), new Output());
  }

  public static void testable(List<DataProvider> providers, Output output) {
    List<String[]> usersData = new ArrayList<>();
    for (DataProvider provider : providers) {
      usersData.addAll(provider.getData());
    }
    output.printUsers(usersData);

    output.printUsers(usersData);
  }

}
