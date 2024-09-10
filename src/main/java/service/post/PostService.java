package service.post;

import java.util.List;

import dto.post.PostDTO;

public interface PostService {
    void doInsertPost(PostDTO postDto);

    List<PostDTO> doGetAllPosts();

    PostDTO dbGetPostById(int id);

    void doUpdatePost(PostDTO postDto);

    void doDeletePost(int id);

	List<PostDTO> doSearchPostsByTitle(String title);
    
}