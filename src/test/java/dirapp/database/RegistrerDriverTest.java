package dirapp.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RegistrerDriverTest {

  // Melakukan registrasi Driver database

  @Test
  void testRegister() {
    try {
      Driver mysqlDriver = new com.mysql.cj.jdbc.Driver();
      DriverManager.registerDriver(mysqlDriver);
    } catch (SQLException exception) {
      Assertions.fail(exception);
    }
  }
}
