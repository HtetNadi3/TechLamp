package controller.comment;


import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.comment.CommentDTO;
import dto.post.PostDTO;
import service.UserService;
import service.UserServiceImpl;
import service.comment.CommentService;
import service.comment.CommentServiceImpl;
import service.post.PostService;
import service.post.PostServiceImpl;
import util.Route;

@WebServlet("/comment/*")
public class CommentController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CommentService commentService;
    private final PostService postService;

    public CommentController() {
        super();
        this.postService = new PostServiceImpl();
        this.commentService = new CommentServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
        try {
            switch (action) {
            case "/new":
                int postId = Integer.parseInt(request.getParameter("postId"));
                PostDTO post = postService.dbGetPostById(postId);
                request.setAttribute("post", post);
                Route.forwardToPage(Route.COMMENT_INSERT_UPDATE_JSP, request, response);
                break;
            case "/add":
                insertComment(request, response);
                break;
            case "/list":
                listComments(request, response, false);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
                updateComment(request, response);
                break;
            case "/delete":
                deleteComment(request, response);
                break;
            default:
                break;
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

    private void insertComment(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        CommentDTO comment = getCommnetParameters(request);
        commentService.doInsertComment(comment);
        Route.redirectToPage("/comment/list?postId=" + comment.getPostId(), request, response);
    }

    private void listComments(HttpServletRequest request, HttpServletResponse response, boolean isSearch)
            throws Exception {
        int postId = Integer.parseInt(request.getParameter("postId"));
        PostDTO post = postService.dbGetPostById(postId);
        List<CommentDTO> comments = commentService.doGetCommentByPostId(postId);
        
        HttpSession session = request.getSession();
        int loggedInUserId = (int) session.getAttribute("userId");

        request.setAttribute("post", post);
        request.setAttribute("comments", comments);
        request.setAttribute("loggedInUserId", loggedInUserId);
        Route.forwardToPage(Route.COMMENT_LIST_JSP, request, response);
    }



    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int commentId = Integer.parseInt(request.getParameter("id"));
        int postId = commentService.doGetCommentById(commentId).getPostId();
        CommentDTO comment = commentService.doGetCommentById(commentId);
        PostDTO post = postService.dbGetPostById(postId);
        request.setAttribute("post", post);
        request.setAttribute("comment", comment);
        request.setAttribute("type", "edit");
        Route.forwardToPage(Route.COMMENT_INSERT_UPDATE_JSP, request, response);
    }

    private void updateComment(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        CommentDTO updatedComment = getCommnetParameters(request);
        commentService.doUpdateComment(updatedComment);
        Route.redirectToPage("/comment/list?postId=" + updatedComment.getPostId(), request, response);
    }

    private void deleteComment(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int commentId = Integer.parseInt(request.getParameter("id"));
        int postId = commentService.doGetCommentById(commentId).getPostId();
        commentService.doDeleteComment(commentId);
        Route.redirectToPage("/comment/list?postId=" + postId, request, response);
    }

    private CommentDTO getCommnetParameters(HttpServletRequest request) {
        String idParam = request.getParameter("id");
        int id = (idParam != null && !idParam.isEmpty()) ? Integer.parseInt(idParam) : 0;
        String description = request.getParameter("description");
        int postId = Integer.parseInt(request.getParameter("postId"));
        HttpSession session = request.getSession();
        int created_user_id = (int) session.getAttribute("userId");
        
        return new CommentDTO(id, description, postId, created_user_id);
    }
}
