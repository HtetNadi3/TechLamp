package servlet.category;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.category.CategoryDTO;
import service.category.CategoryService;
import service.category.CategoryServiceImpl;
import util.Route;

@WebServlet("/category/*")
public class CategoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CategoryService categoryService;

    public CategoryServlet() {
        super();
        this.categoryService = new CategoryServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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

    private void insertCategory(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        CategoryDTO category = getCategoryParameters(request);
        categoryService.doInsertCategory(category);
        Route.redirectToPage("list", response);
    }

    private void listCategories(HttpServletRequest request, HttpServletResponse response, boolean isSearch)
            throws SQLException, IOException, ServletException {
        List<CategoryDTO> categories = categoryService.doGetAllCategories();
        request.setAttribute("categoryList", categories);
        Route.forwardToPage(Route.CATEGORY_LIST_JSP, request, response);
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
        categoryService.doUpdateCategory(updatedCategory);
        Route.redirectToPage("list", response);
    }

    private void deleteCategory(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int categoryId = Integer.parseInt(request.getParameter("id"));
        categoryService.doDeleteCategory(categoryId);
        Route.redirectToPage("list", response);
    }

    private CategoryDTO getCategoryParameters(HttpServletRequest request) {
        String idParam = request.getParameter("id");
        int id = (idParam != null && !idParam.isEmpty()) ? Integer.parseInt(idParam) : 0;
        String name = request.getParameter("name");
        return new CategoryDTO(id, name);
    }
}
