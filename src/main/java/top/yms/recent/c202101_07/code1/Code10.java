package top.yms.recent.c202101_07.code1;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Code10 {
    public static void main(String[] args) {
        TreeMap<Integer, Integer> ins = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1.compareTo(o2) < 0) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });

        ins.put(2,2);
        ins.put(10,10);
        ins.put(3,3);
        ins.put(5,5);
        ins.put(6,6);
        ins.put(7,7);
        ins.put(1,1);
        ins.put(9,9);
        ins.put(4,4);
        ins.put(8,8);

        Set<Map.Entry<Integer, Integer>> entries = ins.entrySet();
        for( Map.Entry<Integer, Integer> entry : entries) {
            System.out.println(entry.getKey()+":"+entry.getValue());
        }


    }
}
