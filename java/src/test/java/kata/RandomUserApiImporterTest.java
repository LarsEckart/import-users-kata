package kata;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class RandomUserApiImporterTest {

    @Test
    void applesauce() {
        var apiImporter = new RandomUserApiImporter("https://randomuser.me/api/?inc=gender,name,email,location,dob&results=2&seed=a9b25cd955e2333h");

        ArrayList<User> users = apiImporter.importUsers();

        Approvals.verifyAll("Imported Users", users);
    }
}
