package servlet;

import bean.Order;
import bean.OrderItem;
import bean.User;
import dao.OrderDAO;
import dao.OrderItemDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderCreateServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (null == user) {
            resp.sendRedirect("/login.jsp");
        }
        Order order = new Order();
        order.setUser(user);
        new OrderDAO().insert(order);

        List<OrderItem> ois = (List<OrderItem>) req.getSession().getAttribute("ois");
        OrderItemDAO orderItemDAO = new OrderItemDAO();
        for (OrderItem oi: ois) {
            oi.setOrder(order);
            orderItemDAO.insert(oi);
        }
        // 生成订单后，清空购物车
        ois.clear();

        // 重定向到listOrder
        resp.sendRedirect("listOrder");
    }
}
