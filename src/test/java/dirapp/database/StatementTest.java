package dirapp.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

public class StatementTest {
  // Statement --> interface untuk mengirim perintah SQL ke database dan menerima response dari databse
  // untuk membuat Statement menggunakan method createStatement() pada connection

  
  // createStatement() --> membuat Statement
  @Test
  void testCreateStatement() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
    Statement statement = connection.createStatement();

    statement.close();
    connection.close();
  }


  // excecuteUpdate(sql) --> mengirim perinta INSERT, UPDATE, DELETE, atau apapun yg tidak butuh result dari database, hanya return int
  @Test
  void testExecuteUpdate() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
    Statement statement = connection.createStatement();

    String sql = """
        INSERT INTO customers(id, name, email) 
        VALUES ('sanjaya', 'Sanjaya', 'sanjaya@email.com')
        """;
    int update = statement.executeUpdate(sql);
    System.out.println(update);

    statement.close();
    connection.close();
  }

  @Test
  void testExecuteDelete() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
    Statement statement = connection.createStatement();

    String sql = """
        DELETE FROM customers;
        """;
    int update = statement.executeUpdate(sql);
    System.out.println(update);

    statement.close();
    connection.close();
  }


  // exexuteQuey(sql) --> mengirim perintah SELECT atau perintah yang menerima result data dari database, returnnya berupa object ResultSet
  @Test
  void testExecuteQuery() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
    Statement statement = connection.createStatement();

    String sql = """
        SELECT * FROM customers
        """;
    ResultSet resultSet = statement.executeQuery(sql);

    resultSet.close();
    statement.close();
    connection.close();
  }
}
