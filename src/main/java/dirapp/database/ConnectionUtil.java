package dirapp.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionUtil {
  // melakukan koneksi ke database dengan connection tidak dianjurkan karena akan memakan resource yang besar dan lambat
  // dianjurkan melakukan koneksi dengan Connection Pool atau disebut dengan DataSource (membuat banyak koneksi diawal, ketika ada request, cukup gunakan koneksi yang telah dibuat)
  // Connection Pool di JDBC direpresentasikan dengan interface javax.sql.DataSource
  // HikariCP adalah salah satu library connection pool yang paling populer

  private static HikariDataSource dataSource;

  static {
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

    // Buat Connection Pool (DataSource)
    dataSource = new HikariDataSource(config);
  }

  public static HikariDataSource getDataSource() {
    return dataSource;
  }
}
