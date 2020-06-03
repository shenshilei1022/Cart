package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static String ip = "127.0.0.1";
    private static int port = 3306;
    private static String database = "cart";
    private static String usr = "root";
    private static String password = "admin";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            String conStr = String.format("jdbc:mysql://%s:%d/%s?serverTimezone=GMT", ip, port, database);
            connection = DriverManager.getConnection(conStr, usr, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
