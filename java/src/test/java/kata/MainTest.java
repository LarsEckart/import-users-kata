package kata;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

class MainTest {


  @Test
  void characterizationTest() {
    PrintStream systemOut = System.out;
    try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos, true, StandardCharsets.UTF_8)) {
      System.setOut(ps);

      Main.main(new String[0]);

      Approvals.verify(baos.toString());
    } catch (Exception e) {
      throw new RuntimeException(e);
    } finally {
      System.setOut(systemOut);
    }
  }
}
