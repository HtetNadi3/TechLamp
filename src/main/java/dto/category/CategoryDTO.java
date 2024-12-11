package dto.category;

import java.util.Date;

import entity.Category;

public class CategoryDTO {
    private int id;
    private String name;
    private boolean deleteFlag;
    private Date createdUserId;
    private Date createdAt;
    private Date updatedAt;
    private int count;

    public CategoryDTO() {
    }

    public CategoryDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.createdAt = category.getCreatedAt();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setCreatedUserId(Date createdUserId) {
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

	

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}