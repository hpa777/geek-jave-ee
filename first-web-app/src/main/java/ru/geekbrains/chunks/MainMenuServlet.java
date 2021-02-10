package ru.geekbrains.chunks;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.IOException;


@WebServlet(urlPatterns = "/main_menu")
public class MainMenuServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cp = req.getContextPath();
        resp.getWriter().println("<ul>\n" +
                "    <li><a href=\"" + cp + "/main\">Главная страница</a></li>\n" +
                "    <li><a href=\"" + cp + "/catalog\">Каталог товаров</a></li>\n" +
                "    <li><a href=\"" + cp + "/product\">Товарная карта</a></li>\n" +
                "    <li><a href=\"" + cp + "/cart\">Корзина</a></li>\n" +
                "    <li><a href=\"" + cp + "/order\">Заказ</a></li>\n" +
                "</ul>");
    }
}
