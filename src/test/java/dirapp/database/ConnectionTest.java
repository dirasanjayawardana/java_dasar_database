package dirapp.database;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

// melakukan koneksi ke database dengan connection tidak dianjurkan karena akan memakan resource yang besar dan lambat
// dianjurkan melakukan koneksi dengan Connection Pool atau disebut dengan DataSource (membuat banyak koneksi diawal, ketika ada request, cukup gunakan koneksi yang telah dibuat)
// HikariCP adalah salah satu library connection pool yang paling populer

public class ConnectionTest {
  
  // Registrasi Driver sebelum melakukan koneksi ke database
  @BeforeAll
  static void beforeAll() {
    try {
      Driver mysqlDriver = new com.mysql.cj.jdbc.Driver();
      DriverManager.registerDriver(mysqlDriver);
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
  }


  // Registrasi Driver jika di code biasa bisa gunakan seperti ini
  // static {
  //   try {
  //     Driver mysqlDriver = new com.mysql.cj.jdbc.Driver();
  //     DriverManager.registerDriver(mysqlDriver);
  //   } catch (SQLException exception) {
  //     exception.printStackTrace();
  //   }
  // }


  // Membuat koneksi ke database
  @Test
  void testConnection() {
    String jdbcUrl =  "jdbc:mysql://localhost:3306/belajar_java_database?serverTimezone=Asia/Jakarta";
    String username = "root";
    String password = "";

    try {
      Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
      System.out.println("Sukses membuat koneksi ke database");
    } catch (SQLException e) {
      Assertions.fail(e);
    }
  }


  // Menutup koneksi ke database --> dengan connection.close()
  // setiap selesai membuat koneksi ke database, maka koneksi wajib ditutup ketika sudah tidak digunakan
  // namun pada unit test akan koneksi akan otomatis ditutup karena program hanya berjalan satu kali, lalu program mati
  @Test
  void testConnectionClose() {
    String jdbcUrl = "jdbc:mysql://localhost:3306/belajar_java_database?serverTimezone=Asia/Jakarta";
    String username = "root";
    String password = "";

    // jika menggunakan try with resource, maka koneksi di parameter blok try akan di close secara otomatis ketika blok try selesai di eksekusi
    try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
      System.out.println("Sukses konek ke database");

      // melakukan close koneksi secara manual
      connection.close();
      System.out.println("Sukses menutup koneksi database");
    } catch (SQLException exception) {
      Assertions.fail(exception);
    }
  }
}
