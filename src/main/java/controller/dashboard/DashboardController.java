package controller.dashboard;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.UserDTO;
import dto.post.PostDTO;
import service.UserService;
import service.UserServiceImpl;
import service.post.PostService;
import service.post.PostServiceImpl;
import util.Route;

@WebServlet("/dashboard")
public class DashboardController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private final PostService postService;
	private final UserService userService;
	public DashboardController() {
        super();
        this.postService = new PostServiceImpl();
        this.userService = new UserServiceImpl();
    }

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			List<PostDTO> posts = postService.doGetAllPosts();
			List<UserDTO> users = userService.getAllUsers();
			
			req.setAttribute("posts", posts);
			req.setAttribute("users", users);
			Route.forwardToPage(Route.DASHBOARD, req, resp);
//			Route.redirectToPage("/dashboard", req, resp);
		
		} catch (Exception e) {
			e.printStackTrace();
	            // Optionally forward to an error page
	            req.setAttribute("errorMessage", "An error occurred while loading the dashboard.");
	           
			
		}
	}
}
