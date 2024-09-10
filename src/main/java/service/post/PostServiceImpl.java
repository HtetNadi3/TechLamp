package service.post;

import java.util.List;
import java.util.stream.Collectors;

import dao.category.CategoryDao;
import dao.category.CategoryDaoImpl;
import dao.post.PostDao;
import dao.post.PostDaoImpl;
import dto.post.PostDTO;
import entity.Post;

public class PostServiceImpl implements PostService {
    private PostDao postDao = new PostDaoImpl();
    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public void doInsertPost(PostDTO postDto) {
        postDao.dbInsertPost(new Post(postDto));
    }

    @Override
    public List<PostDTO> doGetAllPosts() {
        return postDao.dbGetAllPosts().stream().map(post -> {
        	
        	String categoryName = categoryDao.dbGetCategoryById(post.getCategoryId()).getName();
        	String author = postDao.dbFindAuthorByPostId(post.getId());
        	PostDTO postDto = new PostDTO(post);
        	postDto.setAuthor(author);
        	postDto.setCategoryName(categoryName);
        	return postDto;
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
    
    @Override
    public List<PostDTO> doSearchPostsByTitle(String title) {
        List<Post> posts = postDao.dbSearchPostsByTitle(title);
        return posts.stream().map(post -> {
            PostDTO postDTO = new PostDTO();
            postDTO.setId(post.getId());
            postDTO.setTitle(post.getTitle());
            postDTO.setContent(post.getContent());
            postDTO.setCreatedUserId(post.getCreatedUserId());
            postDTO.setCategoryId(post.getCategoryId());
            postDTO.setCreatedAt(post.getCreatedAt());
            return postDTO;
        }).collect(Collectors.toList());
    }
	
}