package top.yms.past11.test;

import jalikejdbc.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

public class HelloTest {
    private static Connection conn = null;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hello?characterEncoding=utf8&useSSL=false", "yms", "123456");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        DB db = new DB(conn);

        final Object [] params = {1};
        Optional<String> username = db.readOnly(s ->
                s.asOne("select * from t_user where id=?", params,
                        rs -> rs.getString("username")));


        db.readOnly(dbs ->
                dbs.foreach("select * from t_user",null,res -> {
                    System.out.println(res.getInt(1)+" "+res.getString("username"));
                }));


    }
}
