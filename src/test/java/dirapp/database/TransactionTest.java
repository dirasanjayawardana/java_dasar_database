package dirapp.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

public class TransactionTest {
  // Pada Database Transaction jika terjadi error maka secara otomatis transaksi akan dirollback/pembatalan, jika tidak ada error maka transaksi akan dicommit/simpan perubahan.
  
  // secara default setiap perintah SQL yang dikirim dengan Connection di JDBC adalah auto commit (langsung djalankan)
  // untuk menggunakan Database Transaction, auto commit di JDBC harus dimatikan, dengan method setAutoCommit(false)
  // commit() --> untuk melakukan commit
  // rollback() --> untuk melakukan rollback/pembatalan

  // Commit adalah perintah untuk menyelesaikan transaksi dan menyimpan semua perubahan yang telah dilakukan selama transaksi tersebut ke dalam database secara permanen. Setelah commit dijalankan, perubahan tidak bisa dibatalkan.

  // Rollback adalah perintah untuk membatalkan semua perubahan yang telah dilakukan selama transaksi dan mengembalikan database ke kondisi sebelum transaksi dimulai. 
  // Ini digunakan untuk memastikan bahwa database tetap dalam keadaan konsisten jika terjadi kesalahan selama transaksi.

  @Test
  void testCommit() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
    connection.setAutoCommit(false);

    String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";
    for (int i = 0; i < 100; i++) {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, "dira@test.com");
      preparedStatement.setString(2, "hi");
      preparedStatement.executeUpdate();
      preparedStatement.close();
    }

    connection.commit();
    connection.close();
  }


  @Test
  void testRollback() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
    connection.setAutoCommit(false);

    String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";
    for (int i = 0; i < 100; i++) {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, "dira@test.com");
      preparedStatement.setString(2, "hi");
      preparedStatement.executeUpdate();
      preparedStatement.close();
    }

    connection.rollback();
    connection.close();
  }
}
