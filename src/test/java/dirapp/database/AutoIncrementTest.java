package dirapp.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

public class AutoIncrementTest {
  // pada JDBC memiliki kemampuan untuk mendapatkan auto generate data seperti auto increment
  // menggunakan method: getGenerateKeys() yang akan mengembalikan ResultSet
  // tambahkan Statement.RETURN_GENERATED_KEYS setelah sql pada prepareStatement() atau executeUpdate() jika menggunakan Statement biasa

  @Test
  void testAutoIncrement() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
    String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";
    PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

    preparedStatement.setString(1, "dira@test.com");
    preparedStatement.setString(2, "hi");

    preparedStatement.executeUpdate();

    ResultSet resultSet = preparedStatement.getGeneratedKeys();
    if (resultSet.next()) {
      System.out.println("Id Comment : " + resultSet.getInt(1));
    }

    resultSet.close();
    preparedStatement.close();
    connection.close();
  }
}
