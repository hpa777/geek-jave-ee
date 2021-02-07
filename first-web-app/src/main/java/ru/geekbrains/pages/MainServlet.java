package ru.geekbrains.pages;

import ru.geekbrains.chunks.PageTitleServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/main/*")
public class MainServlet extends HttpServlet {

    private final static String pageTitle = "Главная страница";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(PageTitleServlet.ATTR, pageTitle);
        getServletContext().getRequestDispatcher("/page_title").include(req, resp);
        getServletContext().getRequestDispatcher("/main_menu").include(req, resp);
    }
}
