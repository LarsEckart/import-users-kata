package kata;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
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

    assertThat(data).containsExactly(
        new String[]{"202489617246", "Germany", "76694", "Alice Smith", "1985-04-23",
            "alice.smith@example.com"},
        new String[]{"202489618679", "USA", "90210", "Bob Johnson",
            "1990-07-15", "bob.johnson@example.com"},
        new String[]{"202489647246", "Spain", "28013",
            "Carlos Diaz", "1978-12-05", "carlos.diaz@example.com"},
        new String[]{"202489717311",
            "China", "100000", "Diana Chen", "1995-03-10", "diana.chen@example.com"});
  }


}
