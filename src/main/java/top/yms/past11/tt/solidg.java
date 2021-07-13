package top.yms.past11.tt;

import java.util.ArrayList;
import java.util.List;

class AA {

    public AA(String str) {
        System.out.println("AA init "+str);
    }
}

public class solidg {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("123");
        list.add("1234");
        list.add("1235");
        list.add("1236");
        list.add("1236dfg");
        list.add("1236sdf");

        List<String> strings = list.subList(0, 2);
        strings.forEach(x -> System.out.println(x));

    }



}
