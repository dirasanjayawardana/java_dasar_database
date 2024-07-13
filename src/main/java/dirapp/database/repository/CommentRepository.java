package dirapp.database.repository;

import java.util.List;

import dirapp.database.entity.Comment;

// Repository --> jembatan antara business logic aplikasi dengan database

public interface CommentRepository {

  void insert(Comment comment);

  Comment findById(Integer id);

  List<Comment> findAll();

  List<Comment> findAllByEmail(String email);

}
