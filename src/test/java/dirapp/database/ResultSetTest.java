package dirapp.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

public class ResultSetTest {
  // ResultSet adalah representasi hasil query dari database, mirip seperti iterator

  @Test
  void testExecuteQuery() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
    Statement statement = connection.createStatement();

    String sql = """
        SELECT * FROM customers
        """;

    ResultSet resultSet = statement.executeQuery(sql);

    while (resultSet.next()) {
      // untuk mendapatkan tiap data, menggunakan --> getTipeData(namaKolom) atau getTipeData(namaTable.namaKolom) 
      String id = resultSet.getString("id");
      String name = resultSet.getString("name");
      String email = resultSet.getString("email");

      System.out.println(
          String.join(", ", id, name, email));
    }

    resultSet.close();
    statement.close();
    connection.close();
  }
}
