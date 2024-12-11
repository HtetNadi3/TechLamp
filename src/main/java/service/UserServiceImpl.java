package service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.mindrot.jbcrypt.BCrypt;
import dao.UserDAO;
import dao.UserDAOImpl;
import dto.UserDTO;
import entity.User;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    public void registerUser(UserDTO userDTO) throws Exception {
        String hashedPassword = BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt());
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(hashedPassword);
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
        user.setCreated_date(userDTO.getCreated_date());
        userDAO.saveUser(user);
    }

    @Override
    public UserDTO loginUser(String username, String password) throws Exception {
        User user = userDAO.findByUsername(username);
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            return new UserDTO(user);
        }
        return null;
    }

   

	@Override
    public UserDTO getUserById(int id) throws Exception {
        User user = userDAO.findById(id);
        return user != null ? new UserDTO(user) : null;
    }

    @Override
    public List<UserDTO> getAllUsers() throws Exception {
        List<User> users = userDAO.findAllUsers();
        return users.stream().map(user -> {
        	UserDTO userDTO = new UserDTO(user);
        	return userDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public void updateUser(UserDTO userDTO) throws Exception {
        User user = userDAO.findById(userDTO.getId());
        if (user != null) {
            user.setUsername(userDTO.getUsername());
            if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
                String hashedPassword = BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt());
                user.setPassword(hashedPassword);
            }
            user.setEmail(userDTO.getEmail());
            user.setRole(userDTO.getRole());
            user.setCreated_date(userDTO.getCreated_date());
            user.setPhone_number(userDTO.getPhone_number());
            user.setBio(userDTO.getBio());
            user.setOccupation(userDTO.getOccupation());
            user.setProfile_img(userDTO.getProfile_img());
            userDAO.updateUser(user);
        }
    }

    @Override
    public void deleteUser(int id) throws Exception {
        userDAO.deleteUser(id);
    }

    @Override
    public int getUserCount() {
    	return userDAO.getUserCount();
    }
    
    @Override
    public List<UserDTO> getRecentUsers(int limit) throws SQLException {
    	List<User> users = userDAO.getRecentUsers(limit);
        return users.stream().map(user -> {
        	UserDTO userDTO = new UserDTO(user);
        	return userDTO;
        }).collect(Collectors.toList());
    }
    
    @Override
    public String getUsernameById(int userId) throws SQLException {
        return userDAO.getUsernameById(userId);
    }
    
    @Override
    public List<UserDTO> searchUsersByUsername(String searchTerm) {
    	List<User> users = userDAO.findUsersByUsername(searchTerm);
        return users.stream().map(user -> {
        	UserDTO userDTO = new UserDTO(user);
        	return userDTO;
        }).collect(Collectors.toList());
    

    }



}
