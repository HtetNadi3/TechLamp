package dao.comment;

import java.sql.SQLException;
import java.util.List;

import entity.Comment;

public interface CommentDao {
    void dbInsertComment(Comment comment);

    List<Comment> dbGetAllComments();

    Comment dbGetCommentById(int id);

    void dbUpdateComment(Comment comment);

    void dbDeleteComment(int id);

    boolean isCommented(int userId);

	int countCommentsByPostId(int postId);

	List<Comment> getCommentsByPostId(int postId);


	String dbGetCommentCreatedUserName(int commentId) throws SQLException;
}