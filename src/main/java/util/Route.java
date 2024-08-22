package util;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Route {
	public static final String USER_LOGIN = "/WEB-INF/jsp/user/login.jsp";
	public static final String USER_REG = "/WEB-INF/jsp/user/reg.jsp";
	public static final String WELCOME = "/welcome.jsp";
	public static final String HOME = "/home.jsp";
	public static final String DASHBOARD = "/WEB-INF/jsp/admin/dashboard.jsp";

	public static void forwardToPage(String page, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

	public static void redirectToPage(String page, HttpServletResponse response) throws IOException {
		response.sendRedirect(page);
	}
}
