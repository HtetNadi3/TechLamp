package controller.user;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import auth.AuthService;
import dao.UserDAO;
import dao.UserDAOImpl;
import entity.User;
import util.Route;

@WebServlet("/")
public class HomeController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final AuthService authService;

    public HomeController() {
        UserDAO userDAO = new UserDAOImpl();
        this.authService = new AuthService(userDAO);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        if (path == null || "/".equals(path)) {
            Route.forwardToPage(Route.WELCOME, request, response);
        } else if ("/register".equals(path)) {
            Route.forwardToPage(Route.USER_REG, request, response);
        } else if ("/login".equals(path)) {
            Route.forwardToPage(Route.USER_LOGIN, request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        if ("/signup".equals(path)) {
            try {
                registerUser(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("/signin".equals(path)) {
            try {
                loginUser(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void handleError(String errorMsg, String page, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("error", errorMsg);
        Route.forwardToPage(page, request, response);
    }

    private void handleSuccessfulLogin(User user, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        session.setAttribute("username", user.getUsername());
        session.setAttribute("role", user.getRole());
        session.setAttribute("userId", user.getId());

        if ("ADMIN".equalsIgnoreCase(user.getRole())) {
            Route.redirectToPage("/dashboard", request, response);
        } else {
            Route.forwardToPage(Route.HOME, request, response);
        }
    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = authService.authenticate(username, password);
        if (user != null) {
            handleSuccessfulLogin(user, request, response);
        } else {
            handleError("Invalid username or password", Route.USER_LOGIN, request, response);
        }
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        Date created_date = new Date();

        if (password == null || !password.equals(confirmPassword)) {
            handleError("Passwords do not match", Route.USER_REG, request, response);
            return;
        }

        if (password.length() < 8 || !password.matches(".*[A-Z].*") || !password.matches(".*[0-9].*")) {
            handleError("Password must be at least 8 characters long and contain an uppercase letter and a number.",
                    Route.USER_REG, request, response);
            return;
        }

        boolean registered = authService.register(username, email, password, created_date);
        if (registered) {
            Route.forwardToPage(Route.USER_LOGIN, request, response);
        } else {
            handleError("Username already exists", Route.USER_REG, request, response);
        }
    }

}
