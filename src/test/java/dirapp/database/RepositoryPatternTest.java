package dirapp.database;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dirapp.database.entity.Comment;
import dirapp.database.repository.CommentRepository;
import dirapp.database.repository.CommentRepositoryImpl;

// Repository --> jembatan antara business logic aplikasi dengan database

public class RepositoryPatternTest {

  private CommentRepository commentRepository;


  @BeforeEach
  void setUp() {
    commentRepository = new CommentRepositoryImpl();
  }


  @Test
  void testInsert() {
    Comment comment = new Comment("dira@test.com", "hi");
    commentRepository.insert(comment);

    Assertions.assertNotNull(comment.getId());
    System.out.println(comment.getId());
  }


  @Test
  void testFindById() {
    Comment comment = commentRepository.findById(2203);
    Assertions.assertNotNull(comment);
    System.out.println(comment.getId());
    System.out.println(comment.getEmail());
    System.out.println(comment.getComment());

    Comment notFound = commentRepository.findById(10000000);
    Assertions.assertNull(notFound);
  }


  @Test
  void testFindAll() {
    List<Comment> comments = commentRepository.findAll();
    System.out.println(comments.size());
  }


  @Test
  void testFindByEmail() {
    List<Comment> comments = commentRepository.findAllByEmail("salah@test.com");
    System.out.println(comments.size());
  }
}
