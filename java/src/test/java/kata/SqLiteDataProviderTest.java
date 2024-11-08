package kata;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SqLiteDataProviderTest {

  private DataProvider dataProvider;

  @BeforeEach
  void setUp() {
    dataProvider = new SqLiteDataProvider("test-users.db");
  }

  @Test
  void testSqLiteDataProviderToProvideUserInfoFromValidDbFile() {
    List<String[]> data = dataProvider.getData();

    Approvals.verifyAll("", data);
  }


}
