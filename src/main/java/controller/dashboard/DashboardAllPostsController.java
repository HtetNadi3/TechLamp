package controller.dashboard;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.post.PostDTO;
import service.post.PostService;
import service.post.PostServiceImpl;
import util.Route;
@WebServlet("/dashboard/allPosts")
public class DashboardAllPostsController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final PostService postService;

    public DashboardAllPostsController() {
        super();
        this.postService = new PostServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
        try {
            if (action != null) {
                switch (action) {
                    case "/delete":
                        deletePost(request, response);
                        break;
                    default:
                        listPosts(request, response);
                        break;
                }
            } else {
                // If no action is provided, default to listing posts
                listPosts(request, response);
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void listPosts(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
    	
        List<PostDTO> posts = postService.doGetAllPosts();
        request.setAttribute("posts", posts);
        Route.forwardToPage(Route.DASHBOARD_ALL_POSTS, request, response);
    }

    private void deletePost(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int postId = Integer.parseInt(request.getParameter("id"));
        postService.doDeletePost(postId);
        Route.redirectToPage("/dashboard/allPosts", request, response);
    }
}


