package dao;

import bean.Order;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    public void insert(Order order) {
        String sql = "INSERT INTO order_ VALUES (null, ?)";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, order.getUser().getId());
            ps.execute();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                order.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Order> list() {
        return list(0, Short.MAX_VALUE);
    }

    public List<Order> list(int start, int count) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM order_";
        try (Connection c = DBUtil.getConnection();
             Statement s = c.createStatement()) {
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setId(rs.getInt("uid"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public List<Order> getOrders(int uid) {
        List<Order> orders = new ArrayList<>();;
        String sql = "SELECT * FROM order_ WHERE uid=?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, uid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setId(rs.getInt("uid"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public Order getOrder(int id) {
        Order order = null;
        String sql = "SELECT * FROM order_ WHERE id=?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                order = new Order();
                order.setId(rs.getInt("id"));
                order.setId(rs.getInt("uid"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

}
