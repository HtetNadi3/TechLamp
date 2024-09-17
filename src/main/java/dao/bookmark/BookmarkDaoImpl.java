package dao.bookmark;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.Bookmark;
import util.DatabaseConnection;

public class BookmarkDaoImpl implements BookmarkDao {
    private Connection connection;
    private final String INSERT_BOOKMARK_SQL = "INSERT INTO bookmark (user_id, post_id, created_at) VALUES (?, ?, ?)";
    private final String SELECT_BOOKMARK_BY_ID = "SELECT * FROM bookmark WHERE user_id = ?";
    private final String DELETE_BOOKMARK_SQL = "DELETE FROM bookmark WHERE id = ?";

    public BookmarkDaoImpl() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void dbAddBookmark(Bookmark bookmark) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(INSERT_BOOKMARK_SQL);
            statement.setInt(1, bookmark.getUserId());
            statement.setInt(2, bookmark.getPostId());
            statement.setTimestamp(3, new Timestamp(new Date().getTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Bookmark> dbGetAllBookmarkByUserId(int userId) {
        List<Bookmark> bookmarks = new ArrayList<>();
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(SELECT_BOOKMARK_BY_ID);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                bookmarks.add(new Bookmark(resultSet.getInt("id"), resultSet.getInt("user_id"),
                        resultSet.getInt("post_id"), resultSet.getDate("created_at")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookmarks;
    }

    @Override
    public void dbDeleteById(int id) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(DELETE_BOOKMARK_SQL);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
