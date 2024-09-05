package dao.comment;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.comment.Comment;
import util.DatabaseConnection;

public class CommentDaoImpl implements CommentDao {
    private Connection connection;
    private final String INSERT_COMMENT_SQL = "INSERT INTO comment (description, post_id, delete_flag, created_user_id, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?)";
    private final String SELECT_ALL_COMMENTS = "SELECT * FROM comment where delete_flag = 0";
    private final String SELECT_COMMENT_BY_ID = "SELECT * FROM comment WHERE id = ? and delete_flag = 0";
    private final String SELECT_COMMENT_BY_POST_ID = "SELECT * FROM comment WHERE post_id = ? and delete_flag = 0";
    private final String UPDATE_COMMENT_SQL = "UPDATE comment SET description = ?,  updated_at=? WHERE id = ?";
    private final String DELETE_COMMENT_SQL = "UPDATE comment SET delete_flag=? WHERE id = ?";
    private final String IS_COMMENTED_SQL = "SELECT COUNT(*) FROM comment where created_user_id = ? and delete_flag = 0";

    public CommentDaoImpl() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void dbInsertComment(Comment comment) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(INSERT_COMMENT_SQL);
            statement.setString(1, comment.getDescription());
            statement.setInt(2, comment.getPostId());
            statement.setInt(3, 0);
            statement.setInt(4, 100);
            statement.setDate(5, new Date(System.currentTimeMillis()));
            statement.setDate(6, null);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Comment> dbGetAllComments() {
        List<Comment> comments = new ArrayList<>();
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_COMMENTS);
            while (resultSet.next()) {
                comments.add(new Comment(resultSet.getInt("id"), resultSet.getString("description"),
                        resultSet.getInt("post_id"), resultSet.getDate("created_at"),
                        resultSet.getInt("created_user_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    @Override
    public Comment dbGetCommentById(int id) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(SELECT_COMMENT_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Comment(resultSet.getInt("id"), resultSet.getString("description"),
                        resultSet.getInt("post_id"), resultSet.getDate("created_at"),
                        resultSet.getInt("created_user_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Comment dbGetCommentByPostId(int postId) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(SELECT_COMMENT_BY_POST_ID);
            statement.setInt(1, postId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Comment(resultSet.getInt("id"), resultSet.getString("description"),
                        resultSet.getInt("post_id"), resultSet.getDate("created_at"),
                        resultSet.getInt("created_user_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void dbUpdateComment(Comment comment) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(UPDATE_COMMENT_SQL);
            statement.setString(1, comment.getDescription());
            statement.setDate(2, new Date(System.currentTimeMillis()));
            statement.setInt(3, comment.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dbDeleteComment(int id) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(DELETE_COMMENT_SQL);
            statement.setInt(1, 1);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isCommented(int userId) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(IS_COMMENTED_SQL);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("COUNT(*)") > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}