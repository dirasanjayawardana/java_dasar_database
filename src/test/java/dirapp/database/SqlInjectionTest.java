package dirapp.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

public class SqlInjectionTest {
  // SQL Injection --> teknik menyalahgunakan celah keamanan yang terjadi dalam lapisan database sebuah aplikasi
  // SQL injection dilakukan dengan mengirim input dari user dengan perintah yang salah, sehingga data tidak valid
  // Statement tidak didesain untuk menangani SQL Injection
  // sehingga sangat tidak disarankan menggunakan statement untuk query yang butuh input parameter
  // untuk menangani SQL Injection, bisa menggunakan interface PreparedStatement

  // code dibawah ini adalah contoh code yang tidak aman
  // ketika username diubah dari "admin" menjadi "admin'; #" maka SQl menjadi tidak valid
  // karena di sql # dianggap sebai komentar, sehingga query password tidak dijalankan

  @Test
  void testSqlInjection() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
    Statement statement = connection.createStatement();

    // ketika username diubah dari "admin" menjadi "admin'; #" maka SQl menjadi tidak valid
    // karena di sql # dianggap sebai komentar, sehingga query password tidak dijalankan
    // String username = "admin";
    String username = "admin'; #";
    String password = "salah";

    String sql = "SELECT * FROM admin WHERE username = '" + username +
        "' AND PASSWORD = '" + password + "'";

    System.out.println(sql);

    ResultSet resultSet = statement.executeQuery(sql);

    if (resultSet.next()) {
      // sukses login
      System.out.println("Sukses login : " + resultSet.getString("username"));
    } else {
      // gagal login
      System.out.println("Gagal login");
    }

    resultSet.close();
    statement.close();
    connection.close();
  }
}
