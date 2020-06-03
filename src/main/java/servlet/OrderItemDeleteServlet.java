package servlet;

import bean.OrderItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderItemDeleteServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pid = Integer.parseInt(req.getParameter("pid"));
        List<OrderItem> ois = (List<OrderItem>) req.getSession().getAttribute("ois");
        for (OrderItem oi : ois) {
            if (pid == oi.getProduct().getId()) {
                ois.remove(oi);
                break;
            }
        }
        req.setAttribute("ois", ois);
        req.getRequestDispatcher("listOrderItem.jsp").forward(req, resp);
    }
}
