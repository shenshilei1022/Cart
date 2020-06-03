package servlet;

import bean.OrderItem;
import bean.Product;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import dao.ProductDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemAddServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取数量，对应产品的pid
        int num = Integer.parseInt(req.getParameter("num"));
        int pid = Integer.parseInt(req.getParameter("pid"));
        // 根据pid获得产品
        Product product = new ProductDAO().getProduct(pid);
        // 得到一条订单项
        OrderItem oi = new OrderItem();
        oi.setProduct(product);
        oi.setNum(num);
        // 从session中获取ois集合，如果是第一次获取，则获取不到，需要新建集合。
        List<OrderItem> ois = (List<OrderItem>) req.getSession().getAttribute("ois");

        if (null == ois) {
            ois = new ArrayList<>();
        }

        boolean add = true;
        for (OrderItem orderItem : ois) {
            if (orderItem.getProduct().getId() == oi.getProduct().getId()) {
                int updateNum = orderItem.getNum() + oi.getNum();
                orderItem.setNum(updateNum);
                add = false;
            }
        }

        if (add) {
            ois.add(oi);
        }

        // 将ois添加到session中
        req.getSession().setAttribute("ois", ois);

        resp.sendRedirect("listProduct");

    }

}
