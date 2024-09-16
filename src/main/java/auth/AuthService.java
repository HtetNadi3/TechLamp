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

	public boolean updateProfile(Integer userId, String username, String email, String password, String phone_number,
			String bio, String occupation, String profileImageName) {
		try {

			User user = userDAO.findById(userId);
			if (user == null) {
				return false;
			}

			user.setUsername(username);
			user.setEmail(email);
			user.setPhone_number(phone_number);
			user.setBio(bio);
			user.setOccupation(occupation);

			if (password != null && !password.isEmpty()) {
				String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
				user.setPassword(hashedPassword);
			}

			if (profileImageName != null && !profileImageName.isEmpty()) {
				user.setProfile_img(profileImageName);
			}

			return userDAO.updateUser(user);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
