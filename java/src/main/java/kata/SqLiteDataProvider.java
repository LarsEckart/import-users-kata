package kata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

class SqLiteDataProvider implements DataProvider {

  private final String dbName;

  public SqLiteDataProvider(String dbName) {
    this.dbName = dbName;
  }

  @Override
  public List<String[]> getData() {
    List<String[]> data = new ArrayList<>();
    String url = "jdbc:sqlite:" + dbName;

    try (Connection conn = DriverManager.getConnection(url);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM users")) {

      while (rs.next()) {
        String[] row = new String[6];
        row[0] = rs.getString("id");
        String location = rs.getString("location");
        String[] split = location.split(", ");
        row[1] = split[0];
        row[2] = split[1];
        row[3] = rs.getString("name");
        row[4] = rs.getString("dob");
        row[5] = rs.getString("email");
        data.add(row);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return data;
  }
}
