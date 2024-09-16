package service.comment;

import java.util.List;
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
            return new CommentDTO(comment);
        }).collect(Collectors.toList());
    }

    @Override
    public CommentDTO doGetCommentById(int id) {
        return new CommentDTO(commentDao.dbGetCommentById(id));
    }

    @Override
    public List<CommentDTO> doGetCommentByPostId(int postId) {
    	return commentDao.getCommentsByPostId(postId).stream().map(comment -> {
            return new CommentDTO(comment);
        }).collect(Collectors.toList());
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