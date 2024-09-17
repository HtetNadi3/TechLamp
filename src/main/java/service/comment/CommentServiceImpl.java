package service.comment;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import dao.comment.CommentDao;
import dao.comment.CommentDaoImpl;
import dto.comment.CommentDTO;
import entity.Comment;

public class CommentServiceImpl implements CommentService {
    private CommentDao commentDao = new CommentDaoImpl();

    @Override
    public void doInsertComment(CommentDTO commentDto) {
        commentDao.dbInsertComment(new Comment(commentDto));
    }

    @Override
    public List<CommentDTO> doGetAllComments() {
        return commentDao.dbGetAllComments().stream().map(comment -> {
            try {
                String commentCreatedUserName = commentDao.dbGetCommentCreatedUserName(comment.getId());
                
                CommentDTO commentDto = new CommentDTO(comment);
                commentDto.setCommentCreatedUserName(commentCreatedUserName);
                return commentDto;
                
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        })
        .filter(Objects::nonNull)
        .collect(Collectors.toList());
    }


    @Override
    public CommentDTO doGetCommentById(int id) {
        return new CommentDTO(commentDao.dbGetCommentById(id));
    }

    @Override
    public List<CommentDTO> doGetCommentByPostId(int postId) {
    	return commentDao.dbGetAllComments().stream().map(comment -> {
            try {
                String commentCreatedUserName = commentDao.dbGetCommentCreatedUserName(comment.getId());
                
                CommentDTO commentDto = new CommentDTO(comment);
                commentDto.setCommentCreatedUserName(commentCreatedUserName);
                return commentDto;
                
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        })
        .filter(Objects::nonNull)
        .collect(Collectors.toList());
    }

    @Override
    public void doUpdateComment(CommentDTO commentDto) {
        commentDao.dbUpdateComment(new Comment(commentDto));
    }

    @Override
    public void doDeleteComment(int id) {
        commentDao.dbDeleteComment(id);
    }

	@Override
	public int getCommentCountByPostId(int postId) {
		return commentDao.countCommentsByPostId(postId);
	}
}