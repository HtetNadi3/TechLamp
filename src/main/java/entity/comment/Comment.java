package entity.comment;

import java.util.Date;

import dto.comment.CommentDTO;

public class Comment {
    private int id;
    private String description;
    private int postId;
    private boolean deleteFlag;
    private int createdUserId;
    private Date createdAt;
    private Date updatedAt;

    public Comment() {
    }

    public Comment(int id, String description, int postId, Date createdAt, int createdUserId) {
        this.id = id;
        this.description = description;
        this.postId = postId;
        this.createdAt = createdAt;
        this.createdUserId = createdUserId;
    }

    public Comment(CommentDTO commentDto) {
        this.id = commentDto.getId();
        this.description = commentDto.getDescription();
        this.postId = commentDto.getPostId();
        this.createdAt = commentDto.getCreatedAt();
        this.createdUserId = commentDto.getCreatedUserId();
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
