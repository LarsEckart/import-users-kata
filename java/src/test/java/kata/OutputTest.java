package kata;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OutputTest {

  private Output output;

  @BeforeEach
  void setUp() {
    output = new Output();
  }

  @Test
  void testOutputToPrintUsersWhenUserListIsEmpty() {
    PrintStream systemOut = System.out;
    try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos, true, StandardCharsets.UTF_8)) {
      System.setOut(ps);

      ArrayList<String[]> usersData = new ArrayList<>();
      output.printUsers(usersData);

      Approvals.verify(baos.toString());
    } catch (Exception e) {
      throw new RuntimeException(e);
    } finally {
      System.setOut(systemOut);
    }
  }

  @Test
  void testPrintingBirthDateFormat() {
    PrintStream systemOut = System.out;
    try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos, true, StandardCharsets.UTF_8)) {
      System.setOut(ps);

      List<String[]> usersData = new CsvProvider("UserInfoWithBirthdate.csv").getData();

      output.printUsers(usersData);

      Approvals.verify(baos.toString());
    } catch (Exception e) {
      throw new RuntimeException(e);
    } finally {
      System.setOut(systemOut);
    }
  }
}
