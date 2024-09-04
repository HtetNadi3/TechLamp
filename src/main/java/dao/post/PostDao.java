package dao.post;

import java.util.List;

import entity.Post;

public interface PostDao {
    void dbInsertPost(Post post);

    List<Post> dbGetAllPosts();

    Post dbGetPostById(int id);

    void dbUpdatePost(Post post);

    void dbDeletePost(int id);
    
    String dbFindAuthorByPostId(int postId);
}
