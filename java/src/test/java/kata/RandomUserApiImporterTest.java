package kata;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

abstract class RandomUserApiImporterTest {

    @Test
    void applesauce() {
        Approvals.settings().allowMultipleVerifyCallsForThisMethod();
        var apiImporter = getImporter();

        List<User> users = apiImporter.importUsers();

        Approvals.verifyAll("Imported Users", users);
    }

    abstract Importer getImporter();

}
