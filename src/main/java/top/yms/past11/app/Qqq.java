package top.yms.past11.app;

import java.util.UUID;

public class Qqq {
    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            UUID uuid = UUID.randomUUID();
            String id = uuid.toString().replaceAll("-", "");
            System.out.println(id);
        }
    }

    public static void main2(String[] args) {
        String str = "110.0";
        String[] split = str.split("\\.");
        for(int i=0; i<split.length; i++) {
            System.out.println(split[i]);
        }

        StringBuilder str3 = new StringBuilder();
        str3.append("hello");
        System.out.println();
//        System.out.println(Integer.parseInt(str));


    }


}
