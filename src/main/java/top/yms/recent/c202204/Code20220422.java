package top.yms.recent.c202204;

import org.junit.jupiter.api.Test;
import top.yms.utils.MyArrays;

public class Code20220422 {

    public void doArrange(char [] array, int s, int l) {

        if (s == l) {
            MyArrays.print(array);
        }

        for(int i = s; i<l; i++) {
            MyArrays.swap(array, i, s);
            doArrange(array, s+1, l);
            MyArrays.swap(array, i, s);
        }


    }

    public  void arrange(char [] array) {
        if (array == null) return;
        int len = array.length;
        if (len == 1) {
            MyArrays.print(array);
            return;
        }
        if (len > 1) {
            doArrange(array, 0, len);
        }
    }

    @Test
    public void testArrange() {

        String str = "ab";
        char [] arr = str.toCharArray();

        arrange(arr);
    }

}
