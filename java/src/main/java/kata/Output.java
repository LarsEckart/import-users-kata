package kata;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

class Output {

  public void printUsers(List<String[]> usersData) {

    // Print users
    println();
    printHeader();
    println();
    printContent(usersData);
    println();
    printSummary(usersData);
  }

  private static void printSummary(List<String[]> usersData) {
    System.out.println(usersData.size() + " users in total!");
  }

  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  private static void printContent(List<String[]> usersData) {
    for (String[] item : usersData) {
      String dobAsString = item[6];
      ZonedDateTime zonedDateTime = ZonedDateTime.parse(dobAsString,
          DateTimeFormatter.ISO_ZONED_DATE_TIME);
      LocalDate localDate = zonedDateTime.toLocalDate();
      String dob = localDate.format(DATE_FORMATTER);

      System.out.println(String.format(
          "* %-12s * %-12s * %-10s * %-20s * %-24s * %-40s *",
          item[0],
          item[3],
          item[4],
          item[2],
          dob,
          item[5]));
    }
  }

  private static void printHeader() {
    System.out.println(String.format(
        "* %-12s * %-12s * %-10s * %-20s * %-24s * %-40s *",
        "ID",
        "COUNTRY",
        "ZIP",
        "NAME",
        "DOB",
        "EMAIL"));
  }

  private static void println() {
    System.out.println(
        "*****************************************************************************************************************************************");
  }
}
