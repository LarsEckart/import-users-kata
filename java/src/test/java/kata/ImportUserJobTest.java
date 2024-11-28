package kata;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ImportUserJobTest {

    @Test
    void applesauce() {
        User alice = testUser("Alice");
        User bob = testUser("Bob");
        var importer = new StubImporter(List.of(alice));
        var importer2 = new StubImporter(List.of(bob));
        var processor = new MockProcessor();
        ImportUserJob importUserJob = new ImportUserJob(List.of(importer, importer2), List.of(processor));

        importUserJob.run();

        assertThat(processor.wasCalledWithUsers(alice, bob)).isTrue();
//        verify(processor).process(ArgumentMatchers.argThat(users -> users.containsAll(List.of(alice, bob))));
    }

    private static User testUser(String name) {
        return new User(3L, name, Birthdate.of(LocalDate.of(2024, 11, 28)), "France", "66860", "any@example.com");
    }

    static class StubImporter implements Importer {
        private final List<User> users;

        public StubImporter(List<User> users) {
            this.users = users;
        }

        @Override
        public List<User> importUsers() {
            return users;
        }
    }

    private class MockProcessor implements Processor {
        private List<User> users;

        @Override
        public void process(List<User> users) {
            this.users = users;
        }

        public List<User> invoked() {
            return users;
        }

        public boolean wasCalledWithUsers(User... users) {
            return this.users.containsAll(List.of(users));
        }
    }
}
