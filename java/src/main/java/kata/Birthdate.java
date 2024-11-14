package kata;

import java.time.LocalDate;
import java.time.ZonedDateTime;

class Birthdate {

  private final LocalDate date;

  private Birthdate(LocalDate date) {
    this.date = date;
  }

  public static Birthdate fromIsoString(String isoFormattedDate) {
    return new Birthdate(ZonedDateTime.parse(isoFormattedDate).toLocalDate());
  }

  public String asString() {
    return date.toString();
  }
  
}
