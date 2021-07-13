package top.yms.past11.tt.xx;

import java.util.ArrayList;
import java.util.List;

public class MyAssert {
    public static void main(String[] args) {
        List<String> l1 = new ArrayList<String>() {{
           add("1");
           add("2");
           add("3");
        }};

        new ArrayList<String>() {{
           l1.stream().forEach(x -> {
               add(x);
           });
        }}.stream().forEach(str -> doRemove(str, l1));

        l1.stream().forEach( x -> System.out.println(x));


    }

    private static void doRemove(String str, List<String> l1) {
        for (int i = 0; i < l1.size(); i++) {
            String st1 = l1.get(i);
            if (st1.equals("2")) {
                l1.remove(i);
                break;
            }
        }
    }
}
