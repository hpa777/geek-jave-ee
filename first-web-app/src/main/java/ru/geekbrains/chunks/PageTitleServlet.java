package ru.geekbrains.chunks;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/page_title")
public class PageTitleServlet extends HttpServlet {

    public final static String ATTR = "pageTitle";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object pageTitle = req.getAttribute(ATTR);
        if (pageTitle != null) {
            resp.getWriter().printf("<h1>%s</h1>%n", pageTitle);
        }
    }
}
