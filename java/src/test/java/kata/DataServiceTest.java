package kata;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataServiceTest {

    private DataProvider dataService;

    @BeforeEach
    void setUp() {
        dataService = new SeededRandomUserDataProvider();
    }

    @Test
    void testDataServiceToProvideUserInfoFromValidCsvFile() {
        String expectedRow1 = "[row1_id, row1_gender, row1_name, row1_country, row1_postcode, row1_email, row1_birthdate]";
        String expectedRow2 = "[row2_id, row2_gender, row2_name, row2_country, row2_postcode, row2_email, row2_birthdate]";

        List<String[]> dataFromCsv = new CsvProvider("ValidUserInfo.csv").getData();

        assertThat(dataFromCsv).isNotEmpty();
        assertThat(dataFromCsv).hasSize(2);
        assertThat(dataFromCsv.get(0)).hasSize(7);
        assertThat(Arrays.toString(dataFromCsv.get(0))).isEqualTo(expectedRow1);
        assertThat(Arrays.toString(dataFromCsv.get(1))).isEqualTo(expectedRow2);
    }

    // TODO: test case for invalid csv file

}
