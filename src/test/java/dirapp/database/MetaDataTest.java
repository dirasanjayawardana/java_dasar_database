package dirapp.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import org.junit.jupiter.api.Test;

public class MetaDataTest {
  // MetaData --> informasi seputar database yang digunakan

  // DatabaseMetaData --> informasi seputar seluruh database yang digunakan (nama database, varsi, table, dll)
  // connection.getMetaData()

  // ParameterMetaData --> informasi parameter yang ada di preparedStatement
  // connection.getParameterMetaData()

  // ResultSetMetaData --> informasi seputar hasil query atau hasil ResultSet
  // resultSet.getMetaData()


  @Test
  void testDatabaseMetaData() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
    DatabaseMetaData databaseMetaData = connection.getMetaData();

    System.out.println(databaseMetaData.getDatabaseProductName());
    System.out.println(databaseMetaData.getDatabaseProductVersion());

    ResultSet resultSet = databaseMetaData.getTables("belajar_java_database", null, null, null);
    while (resultSet.next()) {
      String tableName = resultSet.getString("TABLE_NAME");
      System.out.println("Table : " + tableName);
    }

    connection.close();
  }


  @Test
  void testParameterMetaData() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
    String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);

    ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();

    System.out.println(parameterMetaData.getParameterCount());
    // System.out.println(parameterMetaData.getParameterTypeName(1));

    preparedStatement.close();
    connection.close();
  }
  

  @Test
  void testResultSetMetaData() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery("SELECT * FROM sample_time");

    ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

    for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
      System.out.println("Name : " + resultSetMetaData.getColumnName(i));
      System.out.println("Type : " + resultSetMetaData.getColumnType(i));
      System.out.println("Type Name : " + resultSetMetaData.getColumnTypeName(i));

      if (resultSetMetaData.getColumnType(i) == Types.INTEGER) {
        System.out.println("INI INTEGER");
      }
    }

    resultSet.close();
    statement.close();
    connection.close();
  }
}
