package dto.post;

import java.util.Date;

import entity.post.Post;

public class PostDTO {
    private int id;
    private String title;
    private String content;
    private boolean deleteFlag;
    private Date createdUserId;
    private Date createdAt;
    private Date updatedAt;

    public PostDTO() {
    }

    public PostDTO(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public PostDTO(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Date getCreatedUserId() {
        return createdUserId;
    }

    public void setCreated_user_id(Date createdUserId) {
        this.createdUserId = createdUserId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreated_at(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
