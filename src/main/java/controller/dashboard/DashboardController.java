package controller.dashboard;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.UserDTO;
import dto.category.CategoryDTO;
import dto.post.PostDTO;
import entity.User;
import service.UserService;
import service.UserServiceImpl;
import service.category.CategoryService;
import service.category.CategoryServiceImpl;
import service.post.PostService;
import service.post.PostServiceImpl;
import util.Route;

@WebServlet("/dashboard/*")
public class DashboardController extends HttpServlet {

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("user");
        if (loggedInUser != null) {
            if (loggedInUser.getRole().equalsIgnoreCase("admin")) {
                String action = request.getPathInfo();

                // Handle case where action is null
                if (action == null || action.equals("/")) {
                    action = "/overview"; // Default action
                }

                try {
                    switch (action) {
                    case "/overview":
                        loadDashboardOverview(request, response);
                        break;
                    default:
                        Route.redirectToPage(Route.DASHBOARD, request, response);
                        break;
                    }
                } catch (SQLException ex) {
                    throw new ServletException(ex);
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/error/403");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/error/401");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void loadDashboardOverview(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int userCount = userService.getUserCount();
        int postCount = postService.getPostCount();
        int categoryCount = categoryService.getCategoryCount();

        List<PostDTO> recentPosts = postService.getRecentPosts(5);
        List<UserDTO> recentUsers = userService.getRecentUsers(5);
        List<CategoryDTO> categories = categoryService.doGetAllCategories();

        for (CategoryDTO category : categories) {
            int postCountByCategory = postService.getPostCountByCategory(category.getId());
            category.setPostCount(postCountByCategory);
        }

        request.setAttribute("userCount", userCount);
        request.setAttribute("postCount", postCount);
        request.setAttribute("categoryCount", categoryCount);
        request.setAttribute("recentPosts", recentPosts);
        request.setAttribute("recentUsers", recentUsers);
        request.setAttribute("categories", categories);

        Route.forwardToPage(Route.DASHBOARD, request, response);
    }
}
