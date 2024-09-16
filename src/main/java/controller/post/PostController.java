package controller.post;

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

import dto.UserDTO;
import dto.post.PostDTO;
import service.UserServiceImpl;
import service.category.CategoryServiceImpl;
import service.comment.CommentServiceImpl;
import service.post.PostService;
import service.post.PostServiceImpl;
import util.Route;

@WebServlet("/post/*")
public class PostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final PostService postService;
	private final CategoryServiceImpl categoryService;
	private final UserServiceImpl userService;
	private final CommentServiceImpl commentService;

	public PostController() {
		super();
		this.postService = new PostServiceImpl();
		this.categoryService = new CategoryServiceImpl();
		this.userService = new UserServiceImpl();
		this.commentService = new CommentServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getPathInfo();
		try {
			switch (action) {
			case "/new":
				request.setAttribute("categories", categoryService.doGetAllCategories());
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
		} catch (Exception ex) {
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
		request.setAttribute("categories", categoryService.doGetAllCategories());
		Route.redirectToPage("/post/list", request, response);
	}

	private void listPosts(HttpServletRequest request, HttpServletResponse response, boolean isSearch)
	        throws Exception {

	    String searchTitle = request.getParameter("searchTitle");
	    String categoryIdStr = request.getParameter("categoryId");

	    List<PostDTO> posts;

	    if (searchTitle != null && !searchTitle.trim().isEmpty() && categoryIdStr != null && !categoryIdStr.isEmpty()) {
	        int categoryId = Integer.parseInt(categoryIdStr);
	        posts = postService.searchPostsByTitleAndCategory(searchTitle, categoryId);

	    } else if (categoryIdStr != null && !categoryIdStr.isEmpty()) { 
	        int categoryId = Integer.parseInt(categoryIdStr);
	        posts = postService.getPostsByCategoryId(categoryId);

	    } else if (searchTitle != null && !searchTitle.trim().isEmpty()) {
	        posts = postService.doSearchPostsByTitle(searchTitle);

	    } else { 
	        posts = postService.doGetAllPosts();
	    }

	    List<UserDTO> users = userService.getAllUsers();

	    Map<Integer, Integer> commentCounts = new HashMap<>();
	    for (PostDTO post : posts) {
	        int commentCount = commentService.getCommentCountByPostId(post.getId());
	        commentCounts.put(post.getId(), commentCount);
	    }

	    request.setAttribute("postList", posts);
	    request.setAttribute("users", users);
	    request.setAttribute("commentCounts", commentCounts);
	    
	    request.setAttribute("categories", categoryService.doGetAllCategories());

	    Route.forwardToPage(Route.POST_LIST_JSP, request, response);
	}



	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int postId = Integer.parseInt(request.getParameter("id"));
		PostDTO post = postService.dbGetPostById(postId);
		request.setAttribute("post", post);
		request.setAttribute("categories", categoryService.doGetAllCategories());
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

		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		
		int commentCount = commentService.getCommentCountByPostId(id);

		return new PostDTO(id, title, content, createdUserId, categoryId, commentCount);
	}
}
