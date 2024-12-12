package service.post;

import java.sql.SQLException;
import java.util.List;

import dto.post.PostDTO;
import entity.Post;

public interface PostService {
    void doInsertPost(PostDTO postDto);

    List<PostDTO> doGetAllPosts();

    PostDTO dbGetPostById(int id);

    void doUpdatePost(PostDTO postDto);

    void doDeletePost(int id);

	List<PostDTO> doSearchPostsByTitle(String title);

	int getPostCount() throws SQLException;

	int getPostCountByCategory(int categoryId) throws SQLException;

	int getPostCountByUser(int userId) throws SQLException;

	List<PostDTO> getRecentPosts(int limit) throws SQLException;

	List<PostDTO> getPostsByCategoryId(int categoryId);

	List<PostDTO> searchPostsByTitleAndCategory(String searchTitle, int categoryId);
    
}