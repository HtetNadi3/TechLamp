package dao;

import java.sql.SQLException;
import java.util.List;

import entity.User;


public interface UserDAO {
	boolean saveUser(User user);

	User findByUsername(String username);

	List<User> findAllUsers();

	boolean deleteUser(int id);

	boolean updateUser(User user);

	User findById(int id);


	int getUserCount();

	List<User> getRecentUsers(int limit) throws SQLException;

	String getUsernameById(int userId) throws SQLException;

	List<User> findUsersByUsername(String searchTerm);

	User findByEmail(String email);

}
