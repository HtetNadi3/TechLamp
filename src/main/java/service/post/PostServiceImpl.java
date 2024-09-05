package service.post;

import java.util.List;
import java.util.stream.Collectors;

import dao.comment.CommentDao;
import dao.comment.CommentDaoImpl;
import dao.post.PostDao;
import dao.post.PostDaoImpl;
import dto.post.PostDTO;
import entity.post.Post;

public class PostServiceImpl implements PostService {
    private PostDao postDao = new PostDaoImpl();
    private CommentDao commentDao = new CommentDaoImpl();

    @Override
    public void doInsertPost(PostDTO postDto) {
        postDao.dbInsertPost(new Post(postDto));
    }

    @Override
    public List<PostDTO> doGetAllPosts() {
        return postDao.dbGetAllPosts().stream().map(post -> {
            PostDTO postDto = new PostDTO(post);
            postDto.setIsCommented(commentDao.isCommented(post.getCreatedUserId()));
            return postDto;
        }).collect(Collectors.toList());
    }

    @Override
    public PostDTO doGetPostById(int id) {
        return new PostDTO(postDao.dbGetPostById(id));
    }

    @Override
    public void doUpdatePost(PostDTO postDto) {
        postDao.dbUpdatePost(new Post(postDto));
    }

    @Override
    public void doDeletePost(int id) {
        postDao.dbDeletePost(id);
    }

    @Override
    public boolean isCommented(int userId) {
        return commentDao.isCommented(userId);
    }
}
