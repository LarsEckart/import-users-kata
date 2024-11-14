package kata;

public record User(long id, String name, Birthdate dob, String country, String zip, String email) {

  @Override
  public String toString() {
    return id + "," + name + "," + dob.asString() + "," + country + "," + zip + "," + email;
  }
}
