package kata;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {

    List<Importer> importers = List.of(
        new CsvImporter(),
        new RandomUserApiImporter(),
        new SqLiteImporter()
    );

    List<User> users =
        importers.stream()
            .flatMap(i -> i.importUsers().stream())
            .toList();

    output(users);
    outputToTextFile(users);
  }

  private static void output(List<User> users) {
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

  private static void outputToTextFile(List<User> users) {
    try {
      Files.writeString(Path.of("output.txt"), users.stream().map(User::toString).collect(
          Collectors.joining("\n")));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
