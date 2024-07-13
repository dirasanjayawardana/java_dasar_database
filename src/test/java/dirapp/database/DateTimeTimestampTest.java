package dirapp.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

import org.junit.jupiter.api.Test;

public class DateTimeTimestampTest {
  // pada database ada banyak tipe data waktu, ada Date, Time, dan Timestamp/Datetime
  // pada java hanya ada tipe data java.util.Date
  // untuk menghandle tipe data date dari database, bisa menggunakan class turunan dari java.util.Data pada package java.sql
  // class Date pada package java.sql: Date, Time, Timestamp

  @Test
  void testDate() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
    String sql = """
        INSERT INTO sample_time(sample_time, sample_date, sample_timestamp)
        VALUES (?, ?, ?)
        """;
    PreparedStatement preparedStatement = connection.prepareStatement(sql);

    preparedStatement.setTime(1, new Time(System.currentTimeMillis()));
    preparedStatement.setDate(2, new Date(System.currentTimeMillis()));
    preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

    preparedStatement.executeUpdate();

    preparedStatement.close();
    connection.close();
  }


  @Test
  void testDateQuery() throws SQLException {
    Connection connection = ConnectionUtil.getDataSource().getConnection();
    String sql = "SELECT * FROM sample_time";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    ResultSet resultSet = preparedStatement.executeQuery();

    while (resultSet.next()) {
      Time time = resultSet.getTime("sample_time");
      System.out.println("Time = " + time);

      Date date = resultSet.getDate("sample_date");
      System.out.println("Date = " + date);
      
      Timestamp timestamp = resultSet.getTimestamp("sample_timestamp");
      System.out.println("Timestamp = " + timestamp);
    }

    resultSet.close();
    preparedStatement.close();
    connection.close();
  }
}
