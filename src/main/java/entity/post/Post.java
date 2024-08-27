package entity.post;

import java.util.Date;

import dto.post.PostDTO;

public class Post {
    private int id;
    private String title;
    private String content;
    private boolean deleteFlag;
    private Date createdUserId;
    private String categoryIds;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    public Post() {
    }

    public Post(int id, String title, String content, String categoryIds, Date createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.categoryIds = categoryIds;
        this.createdAt = createdAt;
    }

    public Post(PostDTO postDto) {
        this.id = postDto.getId();
        this.title = postDto.getTitle();
        this.content = postDto.getContent();
        this.categoryIds = postDto.getCategoryIds();
        this.createdAt = postDto.getCreatedAt();
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

    public void setUpdated_at(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeleted_at(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(String categoryIds) {
        this.categoryIds = categoryIds;
    }
}
