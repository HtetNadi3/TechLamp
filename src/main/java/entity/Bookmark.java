package entity;

import java.util.Date;

import dto.bookmark.BookmarkDTO;

public class Bookmark {
    private int id;
    private int userId;
    private int postId;
    private Date createdAt;

    public Bookmark(int id, int userId, int postId, Date createdAt) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.createdAt = createdAt;
    }

    public Bookmark(BookmarkDTO bookmarkDto) {
        this.id = bookmarkDto.getId();
        this.userId = bookmarkDto.getUserId();
        this.postId = bookmarkDto.getPostId();
        this.createdAt = bookmarkDto.getCreatedAt();
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
