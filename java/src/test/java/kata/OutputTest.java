package kata;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.approvaltests.Approvals;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

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

            ArrayList<String[]> usersData = new DataService().getDataFromCsv("UserInfoWithBirthdate.csv");

            output.printUsers(usersData);

            Approvals.verify(baos.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.setOut(systemOut);
        }
    }
}
