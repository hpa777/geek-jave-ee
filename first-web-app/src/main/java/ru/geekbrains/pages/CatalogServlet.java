package ru.geekbrains.pages;

import ru.geekbrains.chunks.PageTitleServlet;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(urlPatterns = "/catalog/*")
public class CatalogServlet extends HttpServlet {

    private final static String pageTitle = "Каталог товаров";

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        this.productRepository = (ProductRepository)getServletContext().getAttribute("productRepository");
        if (this.productRepository == null) {
            throw new ServletException("productRepository not init");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
            req.setAttribute("products", this.productRepository.findAll());
            getServletContext().getRequestDispatcher("/WEB-INF/product_list.jsp").forward(req, resp);
        } else if (req.getPathInfo().equals("/edit")) {
            long id;
            try {
                id = Long.parseLong(req.getParameter("id"));
            } catch (NumberFormatException e) {
                resp.setStatus(400);
                return;
            }
            Product product = (Product) productRepository.findById(id);
            if (product == null) {
                resp.setStatus(404);
                return;
            }
            req.setAttribute("product", product);
            getServletContext().getRequestDispatcher("/WEB-INF/product_form.jsp").forward(req, resp);
        } else if (req.getPathInfo().equals("/delete")) {
            long id;
            try {
                id = Long.parseLong(req.getParameter("id"));
            } catch (NumberFormatException e) {
                resp.setStatus(400);
                return;
            }
            productRepository.deleteById(id);
            resp.sendRedirect(getServletContext().getContextPath() + "/catalog");
        } else if (req.getPathInfo().equals("/new")) {
            getServletContext().getRequestDispatcher("/WEB-INF/product_form.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BigDecimal price;
        try {
            price = new BigDecimal(req.getParameter("price"));
        } catch (NumberFormatException ex) {
            resp.setStatus(400);
            return;
        }
        String strId = req.getParameter("id");
        Product product;
        if (!strId.isEmpty()) {
            long id ;
            try {
                id = Long.parseLong(strId);
            } catch (NumberFormatException ex) {
                resp.setStatus(400);
                return;
            }
            product = new Product(id, req.getParameter("name"), req.getParameter("description"), price);
        } else {
            product = new Product(null, req.getParameter("name"), req.getParameter("description"), price);
        }
        productRepository.saveOrUpdate(product);
        resp.sendRedirect(getServletContext().getContextPath() + "/catalog");
    }

}
