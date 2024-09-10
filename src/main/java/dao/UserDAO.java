package dao;

import java.util.List;

import entity.User;


public interface UserDAO {
	boolean saveUser(User user);

	User findByUsername(String username);

	List<User> findAllUsers();

	boolean deleteUser(int id);

	boolean updateUser(User user);

	User findById(int id);

}
