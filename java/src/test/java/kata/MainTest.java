package kata;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class MainTest {

  @Test
  void contractTestBetweenDataServiceAndOutput() throws IOException {
    Main.testable(new DataService() {
      @Override
      public List<String[]> getDataFromCsv(String csvFileName) {
        List<String[]> list = new ArrayList<>();
        list.add(new String[]{"1"});
        return list;
      }

      @Override
      public List<String[]> getDataFromUrl() {
        List<String[]> list = new ArrayList<>();
        list.add(new String[]{"2"});
        return list;
      }
    }, new Output(){
      @Override
      public void printUsers(List<String[]> usersData) {
        List<String[]> list = new ArrayList<>();
        list.add(new String[]{"1"});
        list.add(new String[]{"2"});

        assertThat(usersData).containsExactlyElementsOf(list);
      }
    });
  }
}
