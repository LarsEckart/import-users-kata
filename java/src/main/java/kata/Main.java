package kata;

import java.util.ArrayList;

public class Main {


    public static void main(String[] args) throws Exception {
        DataService dataService = new DataService();
        ArrayList<String[]> usersData = dataService.getDataFromCsv("users.csv");

        ArrayList<String[]> usersDataFromWeb = dataService.getDataFromUrl();

        /**
         * csv_providers ArrayList<id: number,
         *       email: string
         *       first_name: string
         *       last_name: string>
         */
        usersData.addAll(usersDataFromWeb); // merge arrays

        new Output().printUsers(usersData);
    }

}
