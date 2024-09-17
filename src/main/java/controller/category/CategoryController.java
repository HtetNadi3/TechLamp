package controller.category;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.category.CategoryDTO;
import entity.User;
import service.category.CategoryService;
import service.category.CategoryServiceImpl;
import service.post.PostService;
import service.post.PostServiceImpl;
import util.Route;

@WebServlet("/dashboard/category/*")
public class CategoryController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CategoryService categoryService;
    private final PostService postService;

    public CategoryController() {
        super();
        this.categoryService = new CategoryServiceImpl();
        this.postService = new PostServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("user");
        if (loggedInUser != null) {
            if (loggedInUser.getRole().equals("admin")) {
                String action = request.getPathInfo();
                try {
                    switch (action) {
                    case "/new":
                        Route.forwardToPage(Route.CATEGORY_INSERT_UPDATE_JSP, request, response);
                        break;
                    case "/insert":
                        insertCategory(request, response);
                        break;
                    case "/list":
                        listCategories(request, response, false);
                        break;
                    case "/edit":
                        showEditForm(request, response);
                        break;
                    case "/update":
                        updateCategory(request, response);
                        break;
                    case "/delete":
                        deleteCategory(request, response);
                        break;
                    default:
                        Route.redirectToPage(Route.DASHBOARD_ALL_CATEGORIES, request, response);
                        break;
                    }
                } catch (SQLException ex) {
                    throw new ServletException(ex);
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/error/403");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/error/401");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void insertCategory(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        CategoryDTO category = getCategoryParameters(request);
        if (categoryService.doCheckDuplicateCategoryName(category.getName(), category.getId())) {
            HttpSession session = request.getSession();
            session.setAttribute("message", "Duplicate Category Name!");
            request.setAttribute("category", category);
            Route.forwardToPage(Route.CATEGORY_INSERT_UPDATE_JSP, request, response);
        } else {
            categoryService.doInsertCategory(category);
            Route.redirectToPage("/dashboard/category/list", request, response);
        }
    }

    private void listCategories(HttpServletRequest request, HttpServletResponse response, boolean isSearch)
            throws SQLException, IOException, ServletException {
        List<CategoryDTO> categories = categoryService.doGetAllCategories();
        for (CategoryDTO category : categories) {
            int postCount = postService.getPostCountByCategory(category.getId());
            category.setPostCount(postCount);
        }
        request.setAttribute("categoryList", categories);

        Route.forwardToPage(Route.DASHBOARD_ALL_CATEGORIES, request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int categoryId = Integer.parseInt(request.getParameter("id"));
        CategoryDTO category = categoryService.doGetCategoryById(categoryId);
        request.setAttribute("category", category);
        request.setAttribute("type", "edit");
        Route.forwardToPage(Route.CATEGORY_INSERT_UPDATE_JSP, request, response);
    }

    private void updateCategory(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        CategoryDTO updatedCategory = getCategoryParameters(request);
        if (categoryService.doCheckDuplicateCategoryName(updatedCategory.getName(), updatedCategory.getId())) {
            HttpSession session = request.getSession();
            session.setAttribute("message", "Duplicate Category Name!");
            request.setAttribute("category", updatedCategory);
            Route.forwardToPage(Route.CATEGORY_INSERT_UPDATE_JSP, request, response);
        } else {
            categoryService.doUpdateCategory(updatedCategory);
            Route.redirectToPage("/dashboard/category/list", request, response);
        }
    }

    private void deleteCategory(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int categoryId = Integer.parseInt(request.getParameter("id"));
        categoryService.doDeleteCategory(categoryId);
        Route.redirectToPage("/dashboard/category/list", request, response);
    }

    private CategoryDTO getCategoryParameters(HttpServletRequest request) {
        String idParam = request.getParameter("id");
        int id = (idParam != null && !idParam.isEmpty()) ? Integer.parseInt(idParam) : 0;
        String name = request.getParameter("name");
        return new CategoryDTO(id, name);
    }
}