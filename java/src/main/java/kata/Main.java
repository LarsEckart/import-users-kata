package kata;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) throws Exception {
    doStuff(Main::output);
    doStuff(Main::outputToTextFile);
  }

  private static void doStuff(Consumer<ArrayList<User>> output) throws IOException {
    ArrayList<User> users = new ArrayList<>();

    users.addAll(CsvReader.readCsv());
    users.addAll(RandomUserApiReader.readInternet());

    output.accept(users);
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
          user.dob().asString(),
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
