package top.yms.recent.c202101_07;

import java.util.ArrayList;
import java.util.List;

public class Code212 {
    private static List list = new ArrayList();

    static class A1 {
        public int [] ints = new int[40960];
        public StringBuffer tStr = new StringBuffer(524288); //0.5M
    }

    public static void main(String[] args) {
        A1 a1 = new A1();
        for (int i = 1; i < 100000; i++) {
            new StringBuffer(1024*100);
            try {
                Thread.sleep(1*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i%2 == 0) list.add(new A1());
            if (i%10 ==0 ) list.remove(0);
        }

    }

}
