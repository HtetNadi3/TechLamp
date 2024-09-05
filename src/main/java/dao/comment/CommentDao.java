package dao.comment;

import java.util.List;

import entity.comment.Comment;

public interface CommentDao {
    void dbInsertComment(Comment comment);

    List<Comment> dbGetAllComments();

    Comment dbGetCommentById(int id);

    Comment dbGetCommentByPostId(int postId);

    void dbUpdateComment(Comment comment);

    void dbDeleteComment(int id);

    boolean isCommented(int userId);
}
