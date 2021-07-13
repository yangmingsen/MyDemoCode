package top.yms.oa;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSet {

    private static Connection conn;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection("jdbc:mysql://192.168.164.14:3336/ekp?characterEncoding=utf8&useSSL=false", "yangms", "aw5Zyqaa74Q7RofUsY27jxwpP8NOMIYH");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DataSet(String name) {

    }

    public Connection getConnection() {
        return conn;
    }

    public void close() {

        try {
            conn.close();
        } catch (Exception var2) {
        }

    }
}
