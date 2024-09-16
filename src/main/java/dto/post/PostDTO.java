package dto.post;

import java.util.Date;
import entity.Post;

public class PostDTO {
    private int id;
    private String title;
    private String content;
    private boolean deleteFlag;
    private int createdUserId;
    private int categoryId;
    private Date createdAt;
    private Date updatedAt;
    private String author;
    private String categoryName;

    public PostDTO() {}

    public PostDTO(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public PostDTO(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdUserId = post.getCreatedUserId();
        this.categoryId = post.getCategoryId();
        this.createdAt = post.getCreatedAt();
    }

    public PostDTO(int id, String title, String content, String author, Date createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;
    }

    public PostDTO(int id, String title, String content, int createdUserId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdUserId = createdUserId;
    }

    public PostDTO(int id, String title, String content, int createdUserId,int categoryId, int commentCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdUserId = createdUserId;
        this.categoryId = categoryId;
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

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
