package kata;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

abstract class RandomUserApiImporterTest {

    @Test
    void applesauce() {
        var apiImporter = getImporter("https://randomuser.me/api/?inc=gender,name,email,location,dob&results=2&seed=a9b25cd955e2333h");

        List<User> users = apiImporter.importUsers();

        assertThat(users.get(0).toString()).isEqualTo("100000000001,Mathys Marie,1998-07-19,France,66860,mathys.marie@example.com");
        assertThat(users.get(1).toString()).isEqualTo("100000000002,Ceyhun Yazıcı,1977-01-21,Turkey,16330,ceyhun.yazici@example.com");
    }

    abstract Importer getImporter(String url);

}
