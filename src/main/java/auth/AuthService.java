package auth;

import java.util.Date;

import org.mindrot.jbcrypt.BCrypt;
import dao.UserDAO;
import entity.User;

public class AuthService {
    private UserDAO userDAO;

    public AuthService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean register(String username, String email, String password, Date created_date) {
        if (userDAO.findByUsername(username) != null) {
            return false; 
        }
        
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(hashedPassword);
        user.setRole("USER");
        user.setCreated_date(created_date);
        return userDAO.saveUser(user);
    }

    public User authenticate(String username, String password) {
        User user = userDAO.findByUsername(username);
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            return user;
        }
        return null; 
    }

   
}
