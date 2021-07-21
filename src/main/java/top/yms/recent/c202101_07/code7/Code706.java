package top.yms.recent.c202101_07.code7;

import java.util.ArrayList;

public class Code706 {
    public static void main(String[] args) throws InterruptedException {

        int [] arr = {2,3,4,5,6,7};
        ArrayList<Integer> list = new ArrayList<Integer>() {{
            for(int aa : arr) {
                add(aa);
            }
        }};

        list.stream().forEach(System.out::println);

    }
}
