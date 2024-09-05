package servlet.comment;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.comment.CommentDTO;
import dto.post.PostDTO;
import service.comment.CommentService;
import service.comment.CommentServiceImpl;
import service.post.PostService;
import service.post.PostServiceImpl;
import util.Route;

@WebServlet("/comment/*")
public class CommentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CommentService commentService;
    private final PostService postService;

    public CommentServlet() {
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
                PostDTO post = postService.doGetPostById(postId);
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
        } catch (SQLException ex) {
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
        Route.redirectToPage("list?postId=" + comment.getPostId(), response);
    }

    private void listComments(HttpServletRequest request, HttpServletResponse response, boolean isSearch)
            throws SQLException, IOException, ServletException {
        int postId = Integer.parseInt(request.getParameter("postId"));
        PostDTO post = postService.doGetPostById(postId);
        request.setAttribute("post", post);
        request.setAttribute("comment", commentService.doGetCommentByPostId(postId));
        Route.forwardToPage(Route.COMMENT_LIST_JSP, request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int commentId = Integer.parseInt(request.getParameter("id"));
        int postId = commentService.doGetCommentById(commentId).getPostId();
        CommentDTO comment = commentService.doGetCommentById(commentId);
        PostDTO post = postService.doGetPostById(postId);
        request.setAttribute("post", post);
        request.setAttribute("comment", comment);
        request.setAttribute("type", "edit");
        Route.forwardToPage(Route.COMMENT_INSERT_UPDATE_JSP, request, response);
    }

    private void updateComment(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        CommentDTO updatedComment = getCommnetParameters(request);
        commentService.doUpdateComment(updatedComment);
        Route.redirectToPage("list?postId=" + updatedComment.getPostId(), response);
    }

    private void deleteComment(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int commentId = Integer.parseInt(request.getParameter("id"));
        int postId = commentService.doGetCommentById(commentId).getPostId();
        commentService.doDeleteComment(commentId);
        Route.redirectToPage("list?postId=" + postId, response);
    }

    private CommentDTO getCommnetParameters(HttpServletRequest request) {
        String idParam = request.getParameter("id");
        int id = (idParam != null && !idParam.isEmpty()) ? Integer.parseInt(idParam) : 0;
        String description = request.getParameter("description");
        int postId = Integer.parseInt(request.getParameter("postId"));
        return new CommentDTO(id, description, postId, 100);
    }
}
