package controller.error;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Route;

@WebServlet("/error/*")
public class ErrorController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ErrorController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
        switch (action) {
        case "/401":
            Route.forwardToPage(Route.ERROR_401, request, response);
            break;
        case "/403":
            Route.forwardToPage(Route.ERROR_403, request, response);
            break;
        default:
            break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}
