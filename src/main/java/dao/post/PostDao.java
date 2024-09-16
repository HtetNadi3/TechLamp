package dao.post;

import java.sql.SQLException;
import java.util.List;

import dto.post.PostDTO;
import entity.Post;

public interface PostDao {
    void dbInsertPost(Post post);

    List<Post> dbGetAllPosts();

    Post dbGetPostById(int id);

    void dbUpdatePost(Post post);

    void dbDeletePost(int id);
    
    String dbFindAuthorByPostId(int postId);

	List<Post> dbSearchPostsByTitle(String title);

	int getPostCount() throws SQLException;

	int getPostCountByCategory(int categoryId) throws SQLException;

	int getPostCountByUser(int userId) throws SQLException;

	List<Post> getRecentPosts(int limit) throws SQLException;

	List<Post> dbGetPostsByCategoryId(int categoryId);

	List<Post> findPostsByTitleAndCategory(String searchTitle, int categoryId);
}
