package service.category;

import java.util.List;

import dto.category.CategoryDTO;

public interface CategoryService {
    void doInsertCategory(CategoryDTO categoryDto);

    List<CategoryDTO> doGetAllCategories();

    CategoryDTO doGetCategoryById(int id);

    void doUpdateCategory(CategoryDTO categoryDto);

    void doDeleteCategory(int id);
}