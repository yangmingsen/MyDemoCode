package top.yms.past11.app;

public class MainMain {
    public static void main(String [] args) {
        MyBuider.Builder builder = new MyBuider.Builder();
        builder.setAge("18").setName("yms").setSex(1).build();

        MyBuider.Builder builder1 = new MyBuider.Builder();
        builder1.setAge("18").setName("yms").setSex(1).build();

        System.out.println(builder+"  "+builder1);



    }


}
