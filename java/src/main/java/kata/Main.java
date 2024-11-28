package kata;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Importer> importers = List.of(
                new CsvImporter("users.csv"),
                new ModernRandomUserApiImporter(ModernRandomUserApiImporter.USER_URL),
                new SqLiteImporter("file:users-source.db")
        );

        List<User> users =
                importers.stream()
                        .flatMap(i -> i.importUsers().stream())
                        .toList();

        List.of(
                new ConsoleProcessor(),
                new FileProcessor()
        ).forEach(p -> p.process(users));
    }

}
