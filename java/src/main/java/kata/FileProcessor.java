package kata;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

class FileProcessor implements Processor{

    @Override
    public void process(List<User> users) {
        try {
          Files.writeString(Path.of("output.txt"), users.stream().map(User::toString).collect(
              Collectors.joining("\n")));
        } catch (IOException e) {
          throw new RuntimeException(e);
        }

    }
}
