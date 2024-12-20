package util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Route {
	
	public static final String REGISTER_PATH = "/register";
	
    public static final String USER_LOGIN = "/jsp/user/login.jsp";
    public static final String USER_REG = "/jsp/user/reg.jsp";
    public static final String WELCOME = "/welcome.jsp";
    public static final String HOME = "/home.jsp";
    public static final String DASHBOARD = "/jsp/admin/dashboard.jsp";
    public static final String POST_INSERT_UPDATE_JSP = "/jsp/post/insert_update.jsp";
    public static final String POST_LIST_JSP = "/home.jsp";
    public static final String POST_DETAIL_JSP = "/jsp/post/detail.jsp";
    public static final String CATEGORY_INSERT_UPDATE_JSP = "/jsp/category/insert_update.jsp";
    public static final String CATEGORY_LIST_JSP = "/jsp/category/list.jsp";
    public static final String USER_PROFILE = "/jsp/user/profile.jsp";
    public static final String COMMENT_INSERT_UPDATE_JSP = "/jsp/comment/insert_update.jsp";
    public static final String COMMENT_LIST_JSP = "/jsp/comment/list.jsp";
    public static final String DASHBOARD_ALL_POSTS = "/jsp/admin/dashboardPost.jsp";
    public static final String DASHBOARD_ALL_USERS = "/jsp/admin/dashboardUser.jsp";
    public static final String DASHBOARD_ALL_CATEGORIES = "/jsp/admin/dashboardCategories.jsp";
    public static final String ERROR_401 = "/jsp/error/error401.jsp";
    public static final String ERROR_403 = "/jsp/error/error403.jsp";

    public static void forwardToPage(String page, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    public static void redirectToPage(String path, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        path = request.getServletContext().getContextPath().concat(path);
        response.sendRedirect(path);
    }
}
