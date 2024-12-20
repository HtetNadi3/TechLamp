package controller.user;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import auth.AuthService;
import dao.UserDAO;
import dao.UserDAOImpl;
import dto.UserDTO;
import dto.post.PostDTO;
import entity.User;
import service.UserService;
import service.UserServiceImpl;
import service.post.PostService;
import service.post.PostServiceImpl;
import util.CustomError.ErrorType;
import util.Route;

@WebServlet("/")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final AuthService authService;
	private final UserService userservice;
	private final PostService postService;

	public HomeController() {
		UserDAO userDAO = new UserDAOImpl();
		this.authService = new AuthService(userDAO);
		this.userservice = new UserServiceImpl();
		this.postService = new PostServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();

		if (path == null || "/".equals(path)) {
			Route.forwardToPage(Route.WELCOME, request, response);
		} else if ("/register".equals(path)) {
			Route.forwardToPage(Route.USER_REG, request, response);
		} else if ("/login".equals(path)) {
			Route.forwardToPage(Route.USER_LOGIN, request, response);
		} else if ("/profile".equals(path)) {
			handleProfileRequest(request, response);
		} else if ("/user".equals(path)) {
			handleUserProfileRequest(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		if ("/signup".equals(path)) {
			try {
				registerUser(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if ("/signin".equals(path)) {
			try {
				loginUser(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if ("/profile/update".equals(path)) {
			try {
				updateUserProfile(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	@SuppressWarnings("unused")
	private void handleError(ErrorType type, String errorMsg, String page, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
//        request.setAttribute("error", errorMsg);
		if (type.equals(ErrorType.UserName)) {
			request.getSession(true).setAttribute("userNameError", errorMsg);
		} else if (type.equals(ErrorType.Email)) {
			request.getSession(true).setAttribute("emailError", errorMsg);
		}
//        Route.forwardToPage(page, request, response);
		Route.redirectToPage(page, request, response);
	}

	private void handleError(String errorMsg, String page, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//        request.setAttribute("error", errorMsg);
		request.getSession(true).setAttribute("error", errorMsg);
//        Route.forwardToPage(page, request, response);
		Route.redirectToPage(page, request, response);
	}

	private void handleSuccess(String successMsg, String page, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("success", successMsg);
		Route.forwardToPage(page, request, response);
	}

	private void handleSuccessfulLogin(User user, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = request.getSession();

		session.setAttribute("username", user.getUsername());
		session.setAttribute("role", user.getRole());
		session.setAttribute("userId", user.getId());
		session.setAttribute("user", user);

		if ("ADMIN".equalsIgnoreCase(user.getRole())) {
			Route.redirectToPage("/dashboard", request, response);
		} else {
			Route.redirectToPage("/post/list", request, response);
		}
	}

	private void loginUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		User user = authService.authenticate(username, password);
		if (user != null) {
			handleSuccessfulLogin(user, request, response);
		} else {
			request.getSession(true).setAttribute("loginError", "User name or password is incorrect!");
			Route.redirectToPage("/login", request, response);
//            handleError("Invalid username or password", Route.USER_LOGIN, request, response);
		}
	}

	private void registerUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
//		String confirmPassword = request.getParameter("confirmPassword");
		Date created_date = new Date();

//        if (password == null || !password.equals(confirmPassword)) {
////            handleError("Passwords do not match", Route.USER_REG, request, response);
//            return;
//        }

		if (password.length() < 8 || !password.matches(".*[A-Z].*") || !password.matches(".*[0-9].*")) {
			handleError("Password must be at least 8 characters long and contain an uppercase letter and a number.",
					Route.USER_REG, request, response);
			return;
		} else {

			boolean error = false;

			if (authService.isUserNameExist(username)) {
				request.getSession(true).setAttribute("userNameError", "User name already exists");
				error = true;
			}

			if (authService.isEmailExist(email)) {
				request.getSession(true).setAttribute("emailError", "Email already exists");
				error = true;
//            	handleError(ErrorType.Email, "Email already exists", Route.REGISTER_PATH, request, response);

			}

			if (error) {
				Route.redirectToPage(Route.REGISTER_PATH, request, response);
			} else {
				authService.register(username, email, password, created_date);
				handleSuccess("Registration successful! Please log in.", Route.USER_LOGIN, request, response);
			}

		}

	}

	private void handleProfileRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");

		if (userId != null) {
			try {
				List<PostDTO> postDto = postService.doGetAllPosts().stream()
						.filter(post -> post.getCreatedUserId() == userId).collect(Collectors.toList());

				UserDTO user = userservice.getUserById(userId);

				request.setAttribute("user", user);
				request.setAttribute("postList", postDto);
				Route.forwardToPage(Route.USER_PROFILE, request, response);
			} catch (Exception e) {
				e.printStackTrace();
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		} else {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}

	private void handleUserProfileRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idParam = request.getParameter("id");
		if (idParam != null && !idParam.trim().isEmpty()) {
			try {
				idParam = idParam.replaceAll("[^\\d]", "");
				int userId = Integer.parseInt(idParam);

				UserDTO user = userservice.getUserById(userId);
				if (user != null) {
					List<PostDTO> posts = postService.doGetAllPosts().stream()
							.filter(post -> post.getCreatedUserId() == userId).collect(Collectors.toList());

					request.setAttribute("user", user);
					request.setAttribute("postList", posts);
//	                Route.redirectToPage("/user", request, response);
					Route.forwardToPage(Route.USER_PROFILE, request, response);
				} else {
					response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid user ID format");
			} catch (Exception e) {
				e.printStackTrace();
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		} else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User ID is missing");
		}
	}

	private void updateUserProfile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		if (user == null) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		} else {
			Integer userId = user.getId();
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String phone_number = request.getParameter("phone_number");
			String bio = request.getParameter("bio");
			String occupation = request.getParameter("occupation");
			String profileImageName = null;

			if (password != null && !password.isEmpty()) {
				if (password.length() < 8 || !password.matches(".*[A-Z].*") || !password.matches(".*[0-9].*")) {
					handleError(
							"Password must be at least 8 characters long and contain an uppercase letter and a number.",
							Route.USER_PROFILE, request, response);
					return;
				}
			} else {
				password = null;
			}

			boolean updated = authService.updateProfile(userId, username, email, password, phone_number, bio,
					occupation, profileImageName);
			if (updated) {

				Route.redirectToPage("/profile?id=%s".formatted(user.getId()), request, response);
			} else {
				handleError("Failed to update profile", Route.USER_PROFILE, request, response);
			}
		}
	}

}
