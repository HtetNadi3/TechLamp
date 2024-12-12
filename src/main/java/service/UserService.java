package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import dto.UserDTO;

public interface UserService {
	void registerUser(UserDTO userDTO) throws Exception;
    UserDTO loginUser(String username, String password) throws Exception;
    UserDTO getUserById(int id) throws Exception;
    List<UserDTO> getAllUsers() throws Exception;
    void updateUser(UserDTO userDTO) throws Exception;
    void deleteUser(int id) throws Exception;
	int getUserCount();
	List<UserDTO> getRecentUsers(int limit) throws SQLException;
	String getUsernameById(int userId) throws SQLException;
	List<UserDTO> searchUsersByUsername(String searchTerm);

}
