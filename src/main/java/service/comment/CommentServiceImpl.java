package service.comment;

import java.util.List;
import java.util.stream.Collectors;

import dao.comment.CommentDao;
import dao.comment.CommentDaoImpl;
import dto.comment.CommentDTO;
import entity.comment.Comment;

public class CommentServiceImpl implements CommentService {
    private CommentDao commentDao = new CommentDaoImpl();

    @Override
    public void doInsertComment(CommentDTO commentDto) {
        commentDao.dbInsertComment(new Comment(commentDto));
    }

    @Override
    public List<CommentDTO> doGetAllComments() {
        return commentDao.dbGetAllComments().stream().map(comment -> {
            return new CommentDTO(comment);
        }).collect(Collectors.toList());
    }

    @Override
    public CommentDTO doGetCommentById(int id) {
        return new CommentDTO(commentDao.dbGetCommentById(id));
    }

    @Override
    public CommentDTO doGetCommentByPostId(int postId) {
        Comment comment = commentDao.dbGetCommentByPostId(postId);
        if (comment != null) {
            return new CommentDTO(comment);
        }
        return null;
    }

    @Override
    public void doUpdateComment(CommentDTO commentDto) {
        commentDao.dbUpdateComment(new Comment(commentDto));
    }

    @Override
    public void doDeleteComment(int id) {
        commentDao.dbDeleteComment(id);
    }
}
