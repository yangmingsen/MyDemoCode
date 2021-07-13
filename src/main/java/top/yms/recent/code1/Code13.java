package top.yms.recent.code1;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Code13 {
    public static void main(String[] args) throws Exception {
        List<String> list = Arrays.asList("1", "2", "3", "4");
        for (String i : list) {
            if ("1".equals(i)) {
                list.remove("1");
            }
        }

        System.out.println(list);



    }
}
