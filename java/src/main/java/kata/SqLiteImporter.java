package kata;

import static kata.jooq.tables.Users.USERS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import kata.jooq.tables.records.UsersRecord;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

class SqLiteImporter implements Importer {

  @Override
  public List<User> importUsers() {
    String url = "jdbc:sqlite:file:users-source.db";

    try (Connection conn = DriverManager.getConnection(url)) {
      DSLContext create = DSL.using(conn, SQLDialect.SQLITE);

      Result<UsersRecord> fetch = create.selectFrom(USERS).fetch();

      return fetch.stream().map(
          e -> {
            String location = e.getLocation();
            String[] split = location.split(", ");
            return new User(e.getId(), e.getName(), Birthdate.of(e.getDob()), split[0],
                split[1],
                e.getEmail());
          }).toList();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
