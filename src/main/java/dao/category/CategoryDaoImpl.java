package dao.category;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Category;
import util.DatabaseConnection;

public class CategoryDaoImpl implements CategoryDao {
    private Connection connection;
    private final String INSERT_CATEGORY_SQL = "INSERT INTO category (name, delete_flag, created_at, updated_at, deleted_at) VALUES (?, ?, ?, ?, ?)";
    private final String SELECT_ALL_CATEGORIES = "SELECT * FROM category where delete_flag = 0";
    private final String SELECT_CATEGORY_BY_ID = "SELECT * FROM category WHERE id = ? and delete_flag = 0";
    private final String UPDATE_CATEGORY_SQL = "UPDATE category SET name = ?, updated_at=? WHERE id = ?";
    private final String DELETE_CATEGORY_SQL = "UPDATE category SET delete_flag=?, deleted_at=? WHERE id = ?";
    private final String CHECK_DUPLICATE_CATEGORY_NAME_SQL = "SELECT COUNT(*) > 0 FROM category WHERE name =? AND id <> ? and delete_flag=0";
    private final String POST_COUNT_BY_CATEGORY = "SELECT COUNT(*) FROM post WHERE category_id = ?";

    
    public CategoryDaoImpl() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void dbInsertCategory(Category category) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(INSERT_CATEGORY_SQL);
            statement.setString(1, category.getName());
            statement.setInt(2, 0);
            statement.setDate(3, new Date(System.currentTimeMillis()));
            statement.setDate(4, null);
            statement.setDate(5, null);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Category> dbGetAllCategories() {
        List<Category> categories = new ArrayList<>();
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_CATEGORIES);
            while (resultSet.next()) {
                categories.add(new Category(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getDate("created_at")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public boolean dbCheckDuplicateCategoryName(String name, int id) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(CHECK_DUPLICATE_CATEGORY_NAME_SQL);
            statement.setString(1, name);
            statement.setInt(2, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getBoolean(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Category dbGetCategoryById(int id) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(SELECT_CATEGORY_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Category(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getDate("created_at"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void dbUpdateCategory(Category category) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(UPDATE_CATEGORY_SQL);
            statement.setString(1, category.getName());
            statement.setDate(2, new Date(System.currentTimeMillis()));
            statement.setInt(3, category.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dbDeleteCategory(int id) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(DELETE_CATEGORY_SQL);
            statement.setInt(1, 1);
            statement.setDate(2, new Date(System.currentTimeMillis()));
            statement.setInt(3, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getCategoryCount() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM category";

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    
    @Override
    public int getPostCountByCategory(int categoryId) throws SQLException {
        int count = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(POST_COUNT_BY_CATEGORY)) {

            preparedStatement.setInt(1, categoryId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    count = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

}