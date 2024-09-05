package dto.post;

import java.util.Date;

import entity.post.Post;

public class PostDTO {
    private int id;
    private String title;
    private String content;
    private boolean deleteFlag;
    private int createdUserId;
    private String categoryIds;
    private Date createdAt;
    private Date updatedAt;
    private boolean isCommented;

    public PostDTO() {
    }

    public PostDTO(int id, String title, String content, String categoryIds) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.categoryIds = categoryIds;
    }

    public PostDTO(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.categoryIds = post.getCategoryIds();
        this.createdAt = post.getCreatedAt();
        this.createdUserId = post.getCreatedUserId();
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

    public int getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(int createdUserId) {
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

    public String getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(String categoryIds) {
        this.categoryIds = categoryIds;
    }

    public boolean getIsCommented() {
        return isCommented;
    }

    public void setIsCommented(boolean isCommented) {
        this.isCommented = isCommented;
    }

}
