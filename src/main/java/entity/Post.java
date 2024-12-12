package entity;

import java.util.Date;

import dto.post.PostDTO;

public class Post {
    private int id;
    private String title;
    private String content;
    private boolean deleteFlag;
    private int createdUserId;
    private int categoryId;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    public Post() {
    }

    public Post(int id, String title, String content, Date createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }
    
    public Post(int id, String title, String content,int createdUserId,int categoryIds, Date createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdUserId = createdUserId;
        this.categoryId = categoryIds;
        this.createdAt = createdAt;
    }

    public Post(PostDTO postDto) {
        this.id = postDto.getId();
        this.title = postDto.getTitle();
        this.content = postDto.getContent();
        this.createdUserId = postDto.getCreatedUserId();
        this.categoryId = postDto.getCategoryId();
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

    public int getCreatedUserId() {
        return createdUserId;
    }

    public void setCreated_user_id(int createdUserId) {
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

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

}
