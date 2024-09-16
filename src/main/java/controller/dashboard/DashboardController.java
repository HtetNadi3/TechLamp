package controller.dashboard;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.UserDTO;
import dto.category.CategoryDTO;
import dto.post.PostDTO;
import service.UserService;
import service.UserServiceImpl;
import service.category.CategoryService;
import service.category.CategoryServiceImpl;
import service.post.PostService;
import service.post.PostServiceImpl;
import util.Route;

@WebServlet("/dashboard")
public class DashboardController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private final PostService postService;
	private final UserService userService;
	private final CategoryService categoryService;
	public DashboardController() {
        super();
        this.postService = new PostServiceImpl();
        this.userService = new UserServiceImpl();
        this.categoryService = new CategoryServiceImpl();
    }

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			int userCount = userService.getUserCount();
			int postCount = postService.getPostCount();
			int categoryCount = categoryService.getCategoryCount();
			List<PostDTO> posts = postService.doGetAllPosts();
			List<UserDTO> users = userService.getAllUsers();
			List<CategoryDTO> categories = categoryService.doGetAllCategories();
			List<PostDTO> postsRecent = postService.getRecentPosts(5);
			List<UserDTO> usersRecent = userService.getRecentUsers(5);
			
			req.setAttribute("posts", posts);
			req.setAttribute("users", users);
			req.setAttribute("categories", categories);
			req.setAttribute("userCount", userCount);
			req.setAttribute("postCount", postCount);
			req.setAttribute("categoryCount", categoryCount);
			req.setAttribute("recentPosts", postsRecent);
			req.setAttribute("recentUsers", usersRecent);
			Route.forwardToPage(Route.DASHBOARD, req, resp);
//			Route.redirectToPage("/dashboard", req, resp);
		
		} catch (Exception e) {
			e.printStackTrace();
	            // Optionally forward to an error page
	            req.setAttribute("errorMessage", "An error occurred while loading the dashboard.");
	           
			
		}
	}
}