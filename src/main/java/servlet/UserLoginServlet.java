package servlet;

import bean.User;
import dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserLoginServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        User user = new UserDAO().getUser(name, password);
        if (null != user) {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("listProduct");
        } else {
            resp.sendRedirect("login.jsp");
        }
    }

}
