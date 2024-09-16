package controller.dashboard;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.UserDTO;
import service.UserService;
import service.UserServiceImpl;
import service.post.PostService;
import service.post.PostServiceImpl;
import util.Route;

@WebServlet("/dashboard/allUsers")

public class DashboardAllUsersController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	private final UserService userService;
	private final PostService postService;
	
	public DashboardAllUsersController() {
		super();
		this.userService = new UserServiceImpl();
		this.postService = new PostServiceImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<UserDTO> users;

		try {
			users = userService.getAllUsers();
			for (UserDTO user : users) {
	            int postCount = postService.getPostCountByUser(user.getId());
	            user.setPostCount(postCount);
	        }
			req.setAttribute("users", users);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Route.forwardToPage(Route.DASHBOARD_ALL_USERS, req, resp);
	}
}
