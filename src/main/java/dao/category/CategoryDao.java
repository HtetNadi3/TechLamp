package dao.category;

import java.util.List;

import entity.category.Category;

public interface CategoryDao {
    void dbInsertCategory(Category category);

    List<Category> dbGetAllCategories();

    Category dbGetCategoryById(int id);

    void dbUpdateCategory(Category category);

    void dbDeleteCategory(int id);
}
