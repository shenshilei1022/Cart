package servlet;

import bean.*;
import dao.OrderDAO;
import dao.OrderItemDAO;
import dao.ProductDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderListServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        int uid = user.getId();
        System.out.println(uid);
        // 查找订单
        List<Order> orders = new OrderDAO().getOrders(uid);

        // OrderItem集合用来储存展示订单项的数据
        List<OrderItem> ois = new ArrayList<>();
        OrderItemDAO orderItemDAO = new OrderItemDAO();

        for (Order order : orders) {
            List<OrderItem> orderItems = orderItemDAO.getOrderItems(order.getId());
            ois.addAll(orderItems);
        }

        for (OrderItem orderItem : ois) {
            System.out.println(orderItem.getProduct().getName());
        }

        req.getSession().setAttribute("orderList", ois);
        req.getRequestDispatcher("listOrder.jsp").forward(req, resp);
    }
}
