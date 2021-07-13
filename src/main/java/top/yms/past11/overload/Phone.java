package top.yms.past11.overload;

import java.util.ArrayList;
import java.util.List;

public class Phone {


    public static void main(String [] args) {
        List<String> list =new ArrayList<>();
        list.add("1");
        list.forEach(System.out::println);
    }
}
