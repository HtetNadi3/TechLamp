package dao.category;

import java.sql.SQLException;
import java.util.List;

import entity.Category;

public interface CategoryDao {
    void dbInsertCategory(Category category);

    List<Category> dbGetAllCategories();

    Category dbGetCategoryById(int id);

    boolean dbCheckDuplicateCategoryName(String name, int id);

    void dbUpdateCategory(Category category);

    void dbDeleteCategory(int id);

    int getCategoryCount();

	int getPostCountByCategory(int categoryId) throws SQLException;
}