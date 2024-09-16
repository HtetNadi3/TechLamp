package dao.post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.Post;
import util.DatabaseConnection;

public class PostDaoImpl implements PostDao {
    private Connection connection;
    private final String INSERT_POST_SQL = "INSERT INTO post (title, content, delete_flag, created_user_id, category_id, created_at, updated_at, deleted_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private final String SELECT_ALL_POSTS = "SELECT * FROM post where delete_flag = 0";
    private final String SELECT_POST_BY_ID = "SELECT * FROM post WHERE id = ? and delete_flag = 0";
    private final String UPDATE_POST_SQL = "UPDATE post SET title = ?, content = ?, category_id = ?, updated_at=? WHERE id = ?";
    private final String DELETE_POST_SQL = "UPDATE post SET delete_flag=?, deleted_at=? WHERE id = ?";
    private final String AUTHOR = "SELECT u.username as username FROM post as p JOIN users as u ON p.created_user_id = u.id WHERE p.id = ?";
    private final String SEARCH_POST_BY_TITLE = "SELECT * FROM post WHERE title LIKE ?";
    private final String POST_COUNT = "SELECT COUNT(*) FROM post";
    private final String POST_COUNT_BY_CATEGORY = "SELECT COUNT(*) FROM post WHERE category_id = ?";
    private static final String GET_POST_COUNT_BY_USERID = "SELECT COUNT(*) FROM post WHERE created_user_id = ?";
    private final String RECENT_POSTS = "SELECT * FROM post ORDER BY created_at DESC LIMIT ?";
    private final String POSTS_BY_CATEGORY =  "SELECT * FROM post WHERE category_id = ? AND delete_flag = 0";
    private final String  FindPostsByTitleAndCategory = "SELECT * FROM post WHERE title LIKE ? AND category_id = ? AND delete_flag = 0";


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
            statement.setInt(4, post.getCreatedUserId());
            statement.setInt(5, post.getCategoryId());
            statement.setTimestamp(6, new Timestamp(new Date().getTime()));
            statement.setTimestamp(7, null);
            statement.setTimestamp(8, null);
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
                posts.add(new Post(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("content"), resultSet.getInt("created_user_id"),
                        resultSet.getInt("category_id"), resultSet.getDate("created_at")));
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
                return new Post(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("content"), resultSet.getInt("created_user_id"),
                        resultSet.getInt("category_id"), resultSet.getDate("created_at"));
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
            statement.setInt(3, post.getCategoryId());
            statement.setTimestamp(4, new Timestamp(new Date().getTime()));
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
            statement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));  
            statement.setInt(3, id);  
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String dbFindAuthorByPostId(int postId) {
        String authorName = null;
        try {
            PreparedStatement ps = connection.prepareStatement(AUTHOR);
            ps.setInt(1, postId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                authorName = rs.getString("username");
            }
        } catch (SQLException e) {
            e.printStackTrace();  
        }
        return authorName;
    }
    
    @Override
    public List<Post> dbSearchPostsByTitle(String title) {
        List<Post> posts = new ArrayList<>();
        
        try {
        	PreparedStatement ps = connection.prepareStatement(SEARCH_POST_BY_TITLE);
            ps.setString(1, "%" + title + "%"); 
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                post.setId(rs.getInt("id"));
                post.setTitle(rs.getString("title"));
                post.setContent(rs.getString("content"));
                post.setCreated_user_id(rs.getInt("created_user_id"));
                post.setCategoryId(rs.getInt("category_id"));
                post.setCreated_at(rs.getDate("created_at"));
                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }
    
    @Override
    public int getPostCount() throws SQLException {
        int count = 0;
       
        try ( PreparedStatement ps= connection.prepareStatement(POST_COUNT);
             ResultSet resultSet = ps.executeQuery()) {
            
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return count;
    }
    
    @Override
    public int getPostCountByCategory(int categoryId) throws SQLException {
        int count = 0;
        
        try (
             PreparedStatement preparedStatement = connection.prepareStatement(POST_COUNT_BY_CATEGORY)) {
            
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
    
    @Override
    public int getPostCountByUser(int userId) throws SQLException {
        int postCount = 0;
        try (
             PreparedStatement ps = connection.prepareStatement(GET_POST_COUNT_BY_USERID)) {
            
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    postCount = rs.getInt(1);
                }
            }
        }
        return postCount;
    }
    
    @Override
    public List<Post> getRecentPosts(int limit) throws SQLException {
        List<Post> posts = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(RECENT_POSTS)) {
            stmt.setInt(1, limit);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    posts.add(new Post(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getInt("created_user_id"),
                        rs.getInt("category_id"),
                        rs.getDate("created_at")
                    ));
                }
            }
        }
        return posts;
    }


    @Override
    public List<Post> dbGetPostsByCategoryId(int categoryId) {
        List<Post> posts = new ArrayList<>();


        try (PreparedStatement statement = connection.prepareStatement(POSTS_BY_CATEGORY)) {
            statement.setInt(1, categoryId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
            	Post post = new Post();
                post.setId(rs.getInt("id"));
                post.setTitle(rs.getString("title"));
                post.setContent(rs.getString("content"));
                post.setCreated_user_id(rs.getInt("created_user_id"));
                post.setCategoryId(rs.getInt("category_id"));
                post.setCreated_at(rs.getDate("created_at"));
                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }
    
    @Override
    public List<Post> findPostsByTitleAndCategory(String title, int categoryId) {
        List<Post> posts = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(FindPostsByTitleAndCategory)) {
            statement.setString(1, "%" + title + "%");
            statement.setInt(2, categoryId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
            	Post post = new Post();
                post.setId(rs.getInt("id"));
                post.setTitle(rs.getString("title"));
                post.setContent(rs.getString("content"));
                post.setCreated_user_id(rs.getInt("created_user_id"));
                post.setCategoryId(rs.getInt("category_id"));
                post.setCreated_at(rs.getDate("created_at"));
                posts.add(post);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }




}
