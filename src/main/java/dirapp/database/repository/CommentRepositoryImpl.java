package dirapp.database.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dirapp.database.ConnectionUtil;
import dirapp.database.entity.Comment;

// Repository --> jembatan antara business logic aplikasi dengan database

public class CommentRepositoryImpl implements CommentRepository {

  // Dengan menggunakan try with resource, maka koneksi di parameter blok try akan di close secara otomatis ketika blok try selesai di eksekusi

  @Override
  public void insert(Comment comment) {
    try (Connection connection = ConnectionUtil.getDataSource().getConnection()) {
      String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";

      try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        statement.setString(1, comment.getEmail());
        statement.setString(2, comment.getComment());
        statement.executeUpdate();

        try (ResultSet resultSet = statement.getGeneratedKeys()) {
          if (resultSet.next()) {
            comment.setId(resultSet.getInt(1));
          }
        }
      }
    } catch (SQLException exception) {
      throw new RuntimeException(exception);
    }
  }


  @Override
  public Comment findById(Integer id) {
    try (Connection connection = ConnectionUtil.getDataSource().getConnection()) {
      String sql = "SELECT * FROM comments WHERE id = ?";

      try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setInt(1, id);

        try (ResultSet resultSet = statement.executeQuery()) {
          if (resultSet.next()) {
            return new Comment(
                resultSet.getInt("id"),
                resultSet.getString("email"),
                resultSet.getString("comment")
            );
          } else {
            return null;
          }
        }
      }
    } catch (SQLException exception) {
      throw new RuntimeException(exception);
    }
  }


  @Override
  public List<Comment> findAll() {
    try (Connection connection = ConnectionUtil.getDataSource().getConnection()) {
      String sql = "SELECT * FROM comments";

      try (Statement statement = connection.createStatement()) {

        try (ResultSet resultSet = statement.executeQuery(sql)) {
          List<Comment> comments = new ArrayList<>();

          while (resultSet.next()) {
            comments.add(new Comment(
                resultSet.getInt("id"),
                resultSet.getString("email"),
                resultSet.getString("comment")
            ));
          }
          return comments;
        }
      }
    } catch (SQLException exception) {
      throw new RuntimeException(exception);
    }
  }

  
  @Override
  public List<Comment> findAllByEmail(String email) {
    try (Connection connection = ConnectionUtil.getDataSource().getConnection()) {
      String sql = "SELECT * FROM comments WHERE email = ?";

      try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, email);

        try (ResultSet resultSet = statement.executeQuery()) {
          List<Comment> comments = new ArrayList<>();

          while (resultSet.next()) {
            comments.add(new Comment(
                resultSet.getInt("id"),
                resultSet.getString("email"),
                resultSet.getString("comment")
            ));
          }
          return comments;
        }
      }
    } catch (SQLException exception) {
      throw new RuntimeException(exception);
    }
  }
}
