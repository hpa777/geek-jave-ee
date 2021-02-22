package ru.geekbrains.pages;

import ru.geekbrains.persist.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/category/*")
public class CategoryServlet extends HttpServlet {

    private CategoryRepository categoryRepository;

    @Override
    public void init() throws ServletException {
        this.categoryRepository = (CategoryRepository) getServletContext().getAttribute("categoryRepository");
        if (this.categoryRepository == null) {
            throw new ServletException("categoryRepository not init");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
            req.setAttribute("categorys", this.categoryRepository.findAll());
            getServletContext().getRequestDispatcher("/WEB-INF/category_list.jsp").forward(req, resp);
        } else if (req.getPathInfo().equals("/edit")) {
            long id;
            try {
                id = Long.parseLong(req.getParameter("id"));
            } catch (NumberFormatException e) {
                resp.setStatus(400);
                return;
            }
            Category category = (Category) categoryRepository.findById(id);
            if (category == null) {
                resp.setStatus(404);
                return;
            }
            req.setAttribute("category", category);
            getServletContext().getRequestDispatcher("/WEB-INF/category_form.jsp").forward(req, resp);
        } else if (req.getPathInfo().equals("/delete")) {
            long id;
            try {
                id = Long.parseLong(req.getParameter("id"));
            } catch (NumberFormatException e) {
                resp.setStatus(400);
                return;
            }
            categoryRepository.deleteById(id);
            resp.sendRedirect(getServletContext().getContextPath() + "/category");
        } else if (req.getPathInfo().equals("/new")) {
            getServletContext().getRequestDispatcher("/WEB-INF/category_form.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String strId = req.getParameter("id");
        Category category;
        if (!strId.isEmpty()) {
            long id ;
            try {
                id = Long.parseLong(strId);
            } catch (NumberFormatException ex) {
                resp.setStatus(400);
                return;
            }
            category = new Category(id, req.getParameter("name"), req.getParameter("description"));
        } else {
            category = new Category(null, req.getParameter("name"), req.getParameter("description"));
        }
        categoryRepository.saveOrUpdate(category);
        resp.sendRedirect(getServletContext().getContextPath() + "/category");
    }

}
