package kata;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        new ImportUserJob(
                List.of(
                        new CsvImporter("users.csv"),
                        new ModernRandomUserApiImporter(ModernRandomUserApiImporter.USER_URL),
                        new SqLiteImporter("file:users-source.db")),
                List.of(
                        new ConsoleProcessor(),
                        new FileProcessor()
                )).run();
    }

}
