package dao.post;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.post.Post;
import util.DatabaseConnection;

public class PostDaoImpl implements PostDao {
    private Connection connection;
    private final String INSERT_POST_SQL = "INSERT INTO post (title, content, delete_flag, category_ids, created_user_id, created_at, updated_at, deleted_at) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
    private final String SELECT_ALL_POSTS = "SELECT * FROM post where delete_flag = 0";
    private final String SELECT_POST_BY_ID = "SELECT * FROM post WHERE id = ? and delete_flag = 0";
    private final String UPDATE_POST_SQL = "UPDATE post SET title = ?, content = ?, category_ids = ?,  updated_at=? WHERE id = ?";
    private final String DELETE_POST_SQL = "UPDATE post SET delete_flag=?, deleted_at=? WHERE id = ?";

    public PostDaoImpl() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void dbInsertPost(Post post) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(INSERT_POST_SQL);
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getContent());
            statement.setInt(3, 0);
            statement.setString(4, post.getCategoryIds());
            statement.setInt(5, 100);
            statement.setDate(6, new Date(System.currentTimeMillis()));
            statement.setDate(7, null);
            statement.setDate(8, null);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Post> dbGetAllPosts() {
        List<Post> posts = new ArrayList<>();
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_POSTS);
            while (resultSet.next()) {
                posts.add(new Post(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("content"),
                        resultSet.getString("category_ids"), resultSet.getDate("created_at"),
                        resultSet.getInt("created_user_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public Post dbGetPostById(int id) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(SELECT_POST_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Post(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("content"),
                        resultSet.getString("category_ids"), resultSet.getDate("created_at"),
                        resultSet.getInt("created_user_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void dbUpdatePost(Post post) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(UPDATE_POST_SQL);
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getContent());
            statement.setString(3, post.getCategoryIds());
            statement.setDate(4, new Date(System.currentTimeMillis()));
            statement.setInt(5, post.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dbDeletePost(int id) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(DELETE_POST_SQL);
            statement.setInt(1, 1);
            statement.setDate(2, new Date(System.currentTimeMillis()));
            statement.setInt(3, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
