package dao;

import bean.Product;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public List<Product> list() {
        return list(0, Short.MAX_VALUE);
    }

    public List<Product> list(int start, int count) {
        String sql = "SELECT * FROM product LIMIT ?, ?";
        List<Product> list = new ArrayList<>();
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, start);
            ps.setInt(2, count);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                float price = rs.getFloat("price");
                Product product = new Product();
                product.setId(id);
                product.setName(name);
                product.setPrice(price);
                list.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Product getProduct(int id) {
        String sql = "SELECT * FROM product WHERE id=?";
        Product product = null;
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                product = new Product();
                product.setId(id);
                product.setName(rs.getString("name"));
                product.setPrice(rs.getFloat("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    // 测试代码
//    public static void main(String[] args) {
//        List<Product> list = new ProductDAO().list();
//        for (int i = 0; i < list.size(); i++) {
//            Product product = list.get(i);
//            System.out.println(product.getId() + "   " + product.getName() + "   " + product.getPrice());
//        }
//    }

}
