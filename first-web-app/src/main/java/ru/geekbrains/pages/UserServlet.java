package ru.geekbrains.pages;

import ru.geekbrains.persist.User;
import ru.geekbrains.persist.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/user/*")
public class UserServlet extends HttpServlet {

    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        this.userRepository = (UserRepository) getServletContext().getAttribute("userRepository");
        if (this.userRepository == null) {
            throw new ServletException("userRepository not init");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
            req.setAttribute("users", this.userRepository.findAll());
            getServletContext().getRequestDispatcher("/WEB-INF/user_list.jsp").forward(req, resp);
        } else if (req.getPathInfo().equals("/edit")) {
            long id;
            try {
                id = Long.parseLong(req.getParameter("id"));
            } catch (NumberFormatException e) {
                resp.setStatus(400);
                return;
            }
            User user = (User) userRepository.findById(id);
            if (user == null) {
                resp.setStatus(404);
                return;
            }
            req.setAttribute("user", user);
            getServletContext().getRequestDispatcher("/WEB-INF/user_form.jsp").forward(req, resp);
        } else if (req.getPathInfo().equals("/delete")) {
            long id;
            try {
                id = Long.parseLong(req.getParameter("id"));
            } catch (NumberFormatException e) {
                resp.setStatus(400);
                return;
            }
            userRepository.deleteById(id);
            resp.sendRedirect(getServletContext().getContextPath() + "/user");
        } else if (req.getPathInfo().equals("/new")) {
            getServletContext().getRequestDispatcher("/WEB-INF/user_form.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String strId = req.getParameter("id");
        User user;
        if (!strId.isEmpty()) {
            long id ;
            try {
                id = Long.parseLong(strId);
            } catch (NumberFormatException ex) {
                resp.setStatus(400);
                return;
            }
            user = new User(id, req.getParameter("name"), req.getParameter("email"), req.getParameter("phone"));
        } else {
            user = new User(null, req.getParameter("name"), req.getParameter("email"), req.getParameter("phone"));
        }
        userRepository.saveOrUpdate(user);
        resp.sendRedirect(getServletContext().getContextPath() + "/user");
    }

}
