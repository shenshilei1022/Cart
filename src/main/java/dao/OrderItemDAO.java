package dao;

import bean.Order;
import bean.OrderItem;
import bean.Product;
import bean.User;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAO {

    public void insert(OrderItem oi) {
        String sql = "INSERT INTO orderItem VALUES (null, ?, ?, ?)";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, oi.getProduct().getId());
            ps.setInt(2, oi.getNum());
            ps.setInt(3, oi.getOrder().getId());
            ps.execute();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                oi.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<OrderItem> getOrderItems(int oid) {
        List<OrderItem> orderItems = new ArrayList<>();
        String sql = "SELECT * FROM orderItem WHERE oid=?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, oid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setId(rs.getInt("id"));
                Product product = new ProductDAO().getProduct(rs.getInt("pid"));
                orderItem.setProduct(product);
                orderItem.setNum(rs.getInt("num"));
                Order order = new OrderDAO().getOrder(oid);
                orderItem.setOrder(order);
                orderItems.add(orderItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItems;
    }

}
