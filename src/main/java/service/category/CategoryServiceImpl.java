package service.category;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import dao.category.CategoryDao;
import dao.category.CategoryDaoImpl;
import dto.category.CategoryDTO;
import entity.Category;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public void doInsertCategory(CategoryDTO categoryDto) {
        categoryDao.dbInsertCategory(new Category(categoryDto));
    }

    @Override
    public List<CategoryDTO> doGetAllCategories() {
        return categoryDao.dbGetAllCategories().stream().map(category -> {
        	int Count = 0;
			try {
				Count = categoryDao.getPostCountByCategory(category.getId());
			} catch (SQLException e) {
				e.printStackTrace();
			}
        	CategoryDTO categoryDto = new CategoryDTO(category);
        	categoryDto.setCount(Count);
            return categoryDto;
            return new CategoryDTO(category);
        }).collect(Collectors.toList());
    }

    @Override
    public boolean doCheckDuplicateCategoryName(String name, int id) {
        return categoryDao.dbCheckDuplicateCategoryName(name, id);
    }

    @Override
    public CategoryDTO doGetCategoryById(int id) {
        return new CategoryDTO(categoryDao.dbGetCategoryById(id));
    }

    @Override
    public void doUpdateCategory(CategoryDTO categoryDto) {
        categoryDao.dbUpdateCategory(new Category(categoryDto));
    }

    @Override
    public void doDeleteCategory(int id) {
        categoryDao.dbDeleteCategory(id);
    }

    @Override
    public int getCategoryCount() {
        return categoryDao.getCategoryCount();
    }
}