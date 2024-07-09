package dirapp.database;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionPoolTest {
  // melakukan koneksi ke database dengan connection tidak dianjurkan karena akan memakan resource yang besar dan lambat
  // dianjurkan melakukan koneksi dengan Connection Pool atau disebut dengan DataSource (membuat banyak koneksi diawal, ketika ada request, cukup gunakan koneksi yang telah dibuat)
  // Connection Pool di JDBC direpresentasikan dengan interface javax.sql.DataSource
  // HikariCP adalah salah satu library connection pool yang paling populer

  @Test
  void testHikariCP() {
    // Buat config untuk Connection Pool (DataSource)
    HikariConfig config = new HikariConfig();
    config.setDriverClassName("com.mysql.cj.jdbc.Driver");
    config.setJdbcUrl("jdbc:mysql://localhost:3306/belajar_java_database?serverTimezone=Asia/Jakarta");
    config.setUsername("root");
    config.setPassword("");

    config.setMaximumPoolSize(10); // maksimum koneksi ke database
    config.setMinimumIdle(5); // minimum koneksi ke database ketika tidak ada yang menggunakan koneksi
    config.setIdleTimeout(60_000); // waktu tunggu ketika tidak ada koneksi, maka akan close koneksi, menyisakan min idle
    config.setMaxLifetime(10 * 60_000); // untuk melakukan reconect ulang

    try {
      // Buat Connection Pool (DataSource)
      HikariDataSource dataSource = new HikariDataSource(config);

      // melakukan koneksi ke database dengan Connection Pool (DataSource)
      Connection connection = dataSource.getConnection();

      // ketika melakukan close connection, sebenarnya tidak menutup koneksi, tetapi mengembalikan koneksi ke Connection Pool
      connection.close();

      // menutup semua koneksi di Connection Pool
      dataSource.close();
    } catch (SQLException exception) {
      Assertions.fail(exception);
    }
  }

  @Test
  void testUtil() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
  }
}
