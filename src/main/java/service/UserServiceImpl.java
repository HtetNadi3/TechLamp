package service;

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
        user.setRole(userDTO.getRole());
        userDAO.saveUser(user);
    }

    @Override
    public UserDTO loginUser(String username, String password) throws Exception {
        User user = userDAO.findByUsername(username);
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            return convertToDTO(user);
        }
        return null;
    }

    @Override
    public UserDTO getUserById(int id) throws Exception {
        User user = userDAO.findById(id);
        return user != null ? convertToDTO(user) : null;
    }

    @Override
    public List<UserDTO> getAllUsers() throws Exception {
        List<User> users = userDAO.findAllUsers();
        return users.stream().map(this::convertToDTO).collect(Collectors.toList());
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
            user.setRole(userDTO.getRole());
            userDAO.updateUser(user);
        }
    }

    @Override
    public void deleteUser(int id) throws Exception {
        userDAO.deleteUser(id);
    }

    private UserDTO convertToDTO(User user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getPassword(), user.getRole());
    }
}
