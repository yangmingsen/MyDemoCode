package top.yms.recent.c202204;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class DBTest2022 {

    private static Connection conn;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_blog?characterEncoding=utf8&useSSL=false", "yms", "123456");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void doTest01() throws SQLException {
        String sql = "select * from tag";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        for(int i=1; i<=columnCount; i++) {
            System.out.println(metaData.getColumnName(i));
            System.out.println(metaData.getColumnType(i));
            System.out.println(metaData.getColumnClassName(i));
            System.out.println("---------------------");
        }
        conn.close();
    }



}
