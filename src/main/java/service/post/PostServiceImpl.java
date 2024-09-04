package service.post;

import java.util.List;
import java.util.stream.Collectors;

import dao.post.PostDao;
import dao.post.PostDaoImpl;
import dto.post.PostDTO;
import entity.Post;

public class PostServiceImpl implements PostService {
    private PostDao postDao = new PostDaoImpl();

    @Override
    public void doInsertPost(PostDTO postDto) {
        postDao.dbInsertPost(new Post(postDto));
    }

    @Override
    public List<PostDTO> doGetAllPosts() {
        return postDao.dbGetAllPosts().stream().map(post -> {
        	String author = postDao.dbFindAuthorByPostId(post.getId());
        	return new PostDTO(post.getId(), post.getTitle(), post.getContent(), author, post.getCreatedAt());
        }).collect(Collectors.toList());
    }

    @Override
    public PostDTO dbGetPostById(int id) {
        Post post = postDao.dbGetPostById(id);
        if (post != null) {
            
            String author = postDao.dbFindAuthorByPostId(id);
            return new PostDTO(post.getId(), post.getTitle(), post.getContent(), author, post.getCreatedAt());
        }
        return null;
    }

	@Override
    public void doUpdatePost(PostDTO postDto) {
        postDao.dbUpdatePost(new Post(postDto));
    }

    @Override
    public void doDeletePost(int id) {
        postDao.dbDeletePost(id);
    }
	
}