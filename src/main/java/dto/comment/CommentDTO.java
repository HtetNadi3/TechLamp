package dto.comment;

import java.util.Date;

import entity.comment.Comment;

public class CommentDTO {
    private int id;
    private String description;
    private int postId;
    private boolean deleteFlag;
    private int createdUserId;
    private Date createdAt;
    private Date updatedAt;

    public CommentDTO() {
    }

    public CommentDTO(int id, String description, int postId, int createdUserId) {
        this.id = id;
        this.description = description;
        this.postId = postId;
        this.createdUserId = createdUserId;
    }

    public CommentDTO(Comment comment) {
        this.id = comment.getId();
        this.description = comment.getDescription();
        this.postId = comment.getPostId();
        this.createdAt = comment.getCreatedAt();
        this.createdUserId = comment.getCreatedUserId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
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
}
