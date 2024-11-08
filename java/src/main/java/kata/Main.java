package kata;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        testable(new DataService(), new Output());
    }

    public static void testable(DataService dataService, Output output) throws IOException {
        List<String[]> usersData = dataService.getDataFromCsv("users.csv");
        List<String[]> usersDataFromWeb = dataService.getDataFromUrl();

        usersData.addAll(usersDataFromWeb);

        output.printUsers(usersData);
    }

}
