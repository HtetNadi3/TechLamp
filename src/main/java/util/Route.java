package util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Route {
    public static final String POST_INSERT_UPDATE_JSP = "/jsp/post/insert_update.jsp";
    public static final String POST_LIST_JSP = "/jsp/post/list.jsp";
    public static final String POST_DETAIL_JSP = "/jsp/post/detail.jsp";

    public static void forwardToPage(String page, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    public static void redirectToPage(String page, HttpServletResponse response) throws IOException {
        response.sendRedirect(page);
    }
}
