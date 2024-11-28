package kata;

import java.util.List;

class ConsoleProcessor implements Processor{

    @Override
    public void process(List<User> users) {
        System.out.println(
            "*****************************************************************************************************************************************");
        System.out.println(String.format(
            "* %-12s * %-12s * %-10s * %-20s * %-24s * %-40s *",
            "ID",
            "COUNTRY",
            "ZIP",
            "NAME",
            "DOB",
            "EMAIL"));
        System.out.println(
            "*****************************************************************************************************************************************");
        for (var user : users) {
          System.out.println(String.format(
              "* %-12s * %-12s * %-10s * %-20s * %-24s * %-40s *",
              user.id(),
              user.country(),
              user.zip(),
              user.name(),
              user.dob().asString(),
              user.email()));
        }
        System.out.println(
            "*****************************************************************************************************************************************");
        System.out.println(users.size() + " users in total!");
    }
}
