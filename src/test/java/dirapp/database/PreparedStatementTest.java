package dirapp.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

public class PreparedStatementTest {
  // SQL Injection --> teknik menyalahgunakan celah keamanan yang terjadi dalam lapisan database sebuah aplikasi
  // SQL injection dilakukan dengan mengirim input dari user dengan perintah yang salah, sehingga data tidak valid
  // Statement tidak didesain untuk menangani SQL Injection
  // sehingga sangat tidak disarankan menggunakan statement untuk query yang butuh input parameter
  // untuk menangani SQL Injection, bisa menggunakan interface PreparedStatement

  // PreparedStatement adalah turunan dari Statement, semua yg bisa dilakukan Statement bisa dilakukan di PreparedStatement
  // pada PreparedStatement query sql harus ditentukan diawal, sehingga query tidak bisa berubah-ubah
  // PreparedStatement hanya bisa mengirim untuk satu perintah SQL, jika ada perintah lain, harus buat PreparedStatement lagi
  // untuk input parameter, menggunakan ? dan method setTipedata(keBerapa, valuenya) untuk untuk mengisi parameternya

  // prepareStatement() --> untuk membuat prepareStatement

  @Test
  void testPrepareStatement() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
    String username = "admin'; #";
    String password = "salah";

    String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";

    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setString(1, username);
    preparedStatement.setString(2, password);

    ResultSet resultSet = preparedStatement.executeQuery();
    if (resultSet.next()) {
      // sukses login
      System.out.println("Sukses login : " + resultSet.getString("username"));
    } else {
      // gagal login
      System.out.println("Gagal login");
    }

    preparedStatement.close();
    connection.close();
  }
}
