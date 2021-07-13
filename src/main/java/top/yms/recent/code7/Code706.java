package top.yms.recent.code7;

import java.util.ArrayList;
import java.util.Date;

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
