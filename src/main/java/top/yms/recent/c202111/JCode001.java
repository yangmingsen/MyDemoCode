package top.yms.recent.c202111;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class JCode001 {

    static class Account {
        public String name;
        public String account;
        public String password;

        @Override
        public String toString() {
            return "Account{" +
                    "name='" + name + '\'' +
                    ", account='" + account + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }


    @Test
    public void countPool() throws Exception  {
        Class.forName("com.mysql.jdbc.Driver").newInstance();

        String connectionUrl = "jdbc:mysql://10.24.1.182:3306/vine_test?autoReconnect=true&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";

        String connectionUser = "mycat";
        String connectionPassword = "123456";

        Set<Connection> connSet = new HashSet<>();

        try {
            while (true) {
                Connection conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
                System.out.println("get connection=>"+conn);
                connSet.add(conn);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("获取连接数："+connSet.size());
        connSet.forEach(x -> {
            try {
                x.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });



    }

    @Test
    public void test02() throws Exception {
        String info = "中国移动邮箱 yangmingsen01@139.com KB3P1WcTrJACeydEulqVVw== WPS 15274437065 IDrmNAjKnAFE9SU1AUkfpA== 有道云笔记 yangmingsen16@163.com ZfXuzpkYUJw31Qtaj5xnsw== Google yangmingsen21@gmail.com P7k8u8+mzUKs88qVdkW9OA== 微软账户 1710644559@qq.com 6KAI0ANY75Odhj4XkZbECw== 酷狗音乐 kgopen1033658382 OwdWWgUElr8dLLvB8uFP0g== 钉钉 15274437065 kMHHbuKLW9dXUT9TZE6PFg== Oracle帐户 yangmingsen16@qq.com lV5CJc64Juvhlolri492jQ== 12306账户 YMS199916 +k1GRieVSE1D+ZQl7vryUQ== QQ 578351709 yms123456 OPPO账户 15274437065 IDrmNAjKnAFE9SU1AUkfpA== cloud.oppo.com 15274437065 StQazaSzLk7Q/wp/FGDQgg== id.oppo.com 15274437065 4qoC5Y8Iooxr25txeyMajg== LeetCode 15274437065 dm08IrDee2J90AFxNmx9+w== 百度网盘 yangminsen16 569Hq2SHiDgnrqYP7KbTXg== 智慧树 15274437065 dm08IrDee2J90AFxNmx9+w== WeChat yangmingsen1999 Y4hAnU9sJDJTPQWiRCXNTg== 学信网 15274437065 dm08IrDee2J90AFxNmx9+w== Github yangmingsen o8krLhpUrAsirLirrsHOjA== 向日葵 15274437065 arthoRyLAAA52GP3fxsOOQ== Gitee（码云） 15274437065 dm08IrDee2J90AFxNmx9+w== Postman yangmingsen16 YwE6q97gNRpp04eUJNfMrw==";

        String[] sp2 = info.split(" ");
        List<Account> accounts = new ArrayList<>();
        for(int i=0; i<sp2.length; i+=3) {
            Account account = new Account();
            account.name = sp2[i];
            account.account = sp2[i+1];
            account.password = sp2[i+2];

            accounts.add(account);
        }

        Class.forName("com.mysql.jdbc.Driver").newInstance();

        String connectionUrl = "jdbc:mysql://localhost:3306/yms";

        String connectionUser = "yms";
        String connectionPassword = "123456";
        Connection conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);

        for (Account account : accounts) {
            String sql = "insert into my_account(name,username,password)values(?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1,account.name);
            pst.setString(2,account.account);
            pst.setString(3,account.password);
            System.out.println("After Bind Value:" + pst.toString());
            pst.executeUpdate();
        }

        conn.close();

    }

    @Test
    public void test01() throws Exception{
        Class.forName("com.mysql.jdbc.Driver").newInstance();

        String connectionUrl = "jdbc:mysql://localhost:3306/employees";

        String connectionUser = "yms";
        String connectionPassword = "123456";

        Connection conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);

        String sql = "select * from employees where last_name=? and gender=? LIMIT 0,100";

        PreparedStatement pst = conn.prepareStatement(sql);
        System.out.println("Before Bind Value:" + pst.toString());
        pst.setString(1,"Genin");
        pst.setString(2,"M");
        System.out.println("After Bind Value:" + pst.toString());

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {

            String emp_no = rs.getString("emp_no");
            Date birth_date = rs.getDate("birth_date");
            String first_name = rs.getString("first_name");
            String last_name = rs.getString("last_name");

            System.out.println(emp_no+" "+birth_date+" "+first_name+" "+last_name);
        }



    }
    
}
