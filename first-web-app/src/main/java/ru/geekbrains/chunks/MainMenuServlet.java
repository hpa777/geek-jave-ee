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

        resp.getWriter().println("<ul>\n" +
                "    <li><a href=\"/first-web-app/main\">Главная страница</a></li>\n" +
                "    <li><a href=\"/first-web-app/catalog\">Каталог товаров</a></li>\n" +
                "    <li><a href=\"/first-web-app/product\">Товарная карта</a></li>\n" +
                "    <li><a href=\"/first-web-app/cart\">Корзина</a></li>\n" +
                "    <li><a href=\"/first-web-app/order\">Заказ</a></li>\n" +
                "</ul>");
    }
}
