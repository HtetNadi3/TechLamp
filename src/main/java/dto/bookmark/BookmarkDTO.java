package dto.bookmark;

import java.util.Date;

import entity.Bookmark;

public class BookmarkDTO {
    private int id;
    private int userId;
    private int postId;
    private Date createdAt;

    public BookmarkDTO(Bookmark bookmark) {
        this.id = bookmark.getId();
        this.userId = bookmark.getUserId();
        this.postId = bookmark.getPostId();
        this.createdAt = bookmark.getCreatedAt();
    }

    public BookmarkDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
