package top.yms.recent.c202101_07.code8;

import java.util.*;

public class Code817 {
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();

        Collection collection = new ArrayList();

        Set<String> treeSet = new TreeSet<>();
        Set<String> set = new HashSet<>();

        for(int i=0; i<10; i++) {
            String str = new Random().nextInt()+"";
            treeSet.add(str);
            set.add(str);
        }

        set.forEach(System.out::println);

        System.out.println();

        treeSet.forEach(System.out::println);


    }
}
