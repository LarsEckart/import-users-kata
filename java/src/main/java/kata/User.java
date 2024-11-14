package kata;

import java.time.LocalDate;

public record User(long id, String name, LocalDate dob, String country, String zip, String email) {

}
