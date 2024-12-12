package service.post;

import java.sql.SQLException;
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
			return new PostDTO(post.getId(), post.getTitle(), post.getContent(), author, post.getCreatedAt(), post.getCreatedUserId(), post.getCategoryId());
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
		return postDao.dbSearchPostsByTitle(title).stream().map(post -> {

			String categoryName = categoryDao.dbGetCategoryById(post.getCategoryId()).getName();
			String author = postDao.dbFindAuthorByPostId(post.getId());
			PostDTO postDto = new PostDTO(post);
			postDto.setAuthor(author);
			postDto.setCategoryName(categoryName);
			return postDto;
		}).collect(Collectors.toList());
	}

	@Override
	public int getPostCount() throws SQLException {
		return postDao.getPostCount();
	}

	@Override
	public int getPostCountByCategory(int categoryId) throws SQLException {
		return postDao.getPostCountByCategory(categoryId);
	}

	@Override
	public int getPostCountByUser(int userId) throws SQLException {
		return postDao.getPostCountByUser(userId);
	}

	@Override
	public List<PostDTO> getRecentPosts(int limit) throws SQLException {
		return postDao.getRecentPosts(limit).stream().map(post -> {

			String categoryName = categoryDao.dbGetCategoryById(post.getCategoryId()).getName();
			String author = postDao.dbFindAuthorByPostId(post.getId());
			PostDTO postDto = new PostDTO(post);
			postDto.setAuthor(author);
			postDto.setCategoryName(categoryName);
			return postDto;
		}).collect(Collectors.toList());
	}

	@Override
	public List<PostDTO> getPostsByCategoryId(int categoryId) {
		return postDao.dbGetPostsByCategoryId(categoryId).stream().map(post -> {

			String categoryName = categoryDao.dbGetCategoryById(post.getCategoryId()).getName();
			String author = postDao.dbFindAuthorByPostId(post.getId());
			PostDTO postDto = new PostDTO(post);
			postDto.setAuthor(author);
			postDto.setCategoryName(categoryName);
			return postDto;
		}).collect(Collectors.toList());
	}

	@Override
	public List<PostDTO> searchPostsByTitleAndCategory(String searchTitle, int categoryId) {
		return postDao.findPostsByTitleAndCategory(searchTitle, categoryId).stream().map(post -> {

			String categoryName = categoryDao.dbGetCategoryById(post.getCategoryId()).getName();
			String author = postDao.dbFindAuthorByPostId(post.getId());
			PostDTO postDto = new PostDTO(post);
			postDto.setAuthor(author);
			postDto.setCategoryName(categoryName);
			return postDto;
		}).collect(Collectors.toList());	}

}