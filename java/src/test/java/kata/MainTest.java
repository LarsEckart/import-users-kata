package kata;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.crypto.Data;
import org.junit.jupiter.api.Test;

class MainTest {

  @Test
  void contractTestBetweenDataServiceAndOutput() throws IOException {
    DataProvider csvProvider = new CsvProvider("users.csv") {
      @Override
      public List<String[]> getData() {
        List<String[]> list = new ArrayList<>();
        list.add(new String[]{"1"});
        return list;
      }
    };
    DataProvider webProvider = new SeededRandomUserDataProvider() {
      public List<String[]> getData() {
        List<String[]> list = new ArrayList<>();
        list.add(new String[]{"2"});
        return list;
      }
    };

    Output testOutput = new Output() {
      @Override
      public void printUsers(List<String[]> usersData) {
        List<String[]> list = new ArrayList<>();
        list.add(new String[]{"1"});
        list.add(new String[]{"2"});

        assertThat(usersData).containsExactlyElementsOf(list);
      }
    };
    Main.testable(List.of(csvProvider, webProvider), testOutput);
  }
}
