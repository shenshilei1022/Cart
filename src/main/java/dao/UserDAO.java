package dao;

import bean.User;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // 根据传入的name和password查询用户是否存在(返回null表示不存在)
    public User getUser(String name, String password) {
        User user = null;
        String sql = "SELECT * FROM user WHERE name=? AND password=?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> list() {
        return list(0, Short.MAX_VALUE);
    }

    public List<User> list(int start, int count) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user";
        try (Connection c = DBUtil.getConnection();
             Statement s = c.createStatement()) {
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // 测试代码
//    public static void main(String[] args) {
//        User user = new UserDAO().getUser("shenshilei", "admin");
//        System.out.println(user.getId() + "  " + user.getName() + "  " + user.getPassword());
//    }

}
