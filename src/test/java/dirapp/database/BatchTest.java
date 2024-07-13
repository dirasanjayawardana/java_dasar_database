package dirapp.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

public class BatchTest {
  // secara default komunikasi antara database dengan aplikasi java adalah request dan response
  // aplikasi akan mengirim request dan menunggu response dari database
  // ini menjadi masalah jika ingin mengirim atau import data dalam jumlah besar

  // Batch Proses adalah proses mengirim perintah secara banyak sekaligus
  // semua proses Batch akan disimpan di memory, jadi hati hati jangan terlalu banyak
  // addBatch() --> perintah-perintah sql akan ditampung
  // executeBatch() --> melakukan eksekusi perintah-perintah yang sudah ditampung
  // clearParameters() --> menghapus parameter input user sebelumnya

  @Test
  void testStatement() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
    Statement statement = connection.createStatement();

    String sql = "INSERT INTO comments(email, comment) VALUES ('dira@test.com', 'hi')";

    for (int i = 0; i < 1000; i++) {
      statement.addBatch(sql);
    }

    statement.executeBatch();

    statement.close();
    connection.close();
  }

  @Test
  void testPreparedStatement() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
    String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);

    for (int i = 0; i < 1000; i++) {
      preparedStatement.clearParameters();
      preparedStatement.setString(1, "dira@test.com");
      preparedStatement.setString(2, "hi");
      preparedStatement.addBatch();
    }

    preparedStatement.executeBatch();

    preparedStatement.close();
    connection.close();
  }
}
