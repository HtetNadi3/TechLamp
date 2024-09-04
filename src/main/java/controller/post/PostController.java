package controller.post;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.post.PostDTO;
import service.post.PostService;
import service.post.PostServiceImpl;
import util.Route;

@WebServlet("/post/*")
public class PostController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final PostService postService;

    public PostController() {
        super();
        this.postService = new PostServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
        try {
            switch (action) {
            case "/new":
                Route.forwardToPage(Route.POST_INSERT_UPDATE_JSP, request, response);
                break;
            case "/insert":
                insertPost(request, response);
                break;
            case "/list":
                listPosts(request, response, false);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
                updatePost(request, response);
                break;
            case "/delete":
                deletePost(request, response);
                break;
            case "/detail":
                detailPost(request, response);
                break;
            default:
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void insertPost(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        PostDTO post = getPostParameters(request);
        postService.doInsertPost(post);
        Route.redirectToPage("/post/list", request, response);
    }

    private void listPosts(HttpServletRequest request, HttpServletResponse response, boolean isSearch)
            throws SQLException, IOException, ServletException {
        List<PostDTO> posts = postService.doGetAllPosts();
        request.setAttribute("postList", posts);
        Route.forwardToPage(Route.POST_LIST_JSP, request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int postId = Integer.parseInt(request.getParameter("id"));
        PostDTO post = postService.dbGetPostById(postId);
        request.setAttribute("post", post);
        request.setAttribute("type", "edit");
        Route.forwardToPage(Route.POST_INSERT_UPDATE_JSP, request, response);
    }

    private void updatePost(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        PostDTO updatedPost = getPostParameters(request);
        postService.doUpdatePost(updatedPost);
        Route.redirectToPage("/post/list", request, response);
    }

    private void deletePost(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int postId = Integer.parseInt(request.getParameter("id"));
        postService.doDeletePost(postId);
        Route.redirectToPage("/post/list", request, response);
    }

    private void detailPost(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int postId = Integer.parseInt(request.getParameter("id"));
        PostDTO post = postService.dbGetPostById(postId);
        request.setAttribute("post", post);
        Route.forwardToPage(Route.POST_DETAIL_JSP, request, response);
    }

    private PostDTO getPostParameters(HttpServletRequest request) {
        String idParam = request.getParameter("id");
        int id = (idParam != null && !idParam.isEmpty()) ? Integer.parseInt(idParam) : 0;
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        
        HttpSession session = request.getSession();
        int createdUserId = (int) session.getAttribute("userId");
     
        Date createdAt = new Date();
        return new PostDTO(id, title, content, createdUserId, createdAt);
    }
}