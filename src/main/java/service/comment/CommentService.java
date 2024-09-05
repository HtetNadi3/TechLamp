package service.comment;

import java.util.List;

import dto.comment.CommentDTO;

public interface CommentService {
    void doInsertComment(CommentDTO postDto);

    List<CommentDTO> doGetAllComments();

    CommentDTO doGetCommentById(int id);

    CommentDTO doGetCommentByPostId(int id);

    void doUpdateComment(CommentDTO commentDto);

    void doDeleteComment(int id);
}