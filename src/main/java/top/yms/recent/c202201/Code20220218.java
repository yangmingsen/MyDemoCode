package top.yms.recent.c202201;


import org.junit.jupiter.api.Test;
import top.yms.utils.ListNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static top.yms.past11.solution.Solution.swap;

public class Code20220218 {



    public int ipvot(int [] arr, int l, int r) {
        int pivot = arr[r];
        for(int i=l; i<r; i++) {
            if (pivot > arr[i]) {
                swap(arr, l++, i);
            }
        }
        swap(arr, l, r);

        return l;
    }

    public void qSort(int [] arr, int l, int r) {
        if (l < r) {
            int p = ipvot(arr, l, r);
            qSort(arr, l, p-1);
            qSort(arr, p+1, r);
        }
    }

    public void qSort(int [] arr) {
        if (arr == null) return;
        int r = arr.length;
        if (r == 1) return;
        qSort(arr, 0, r-1);
    }

    @Test
    public void testQSort() {
        int[] ints = ListNode.autoGen(10);
        System.out.println(Arrays.toString(ints));
        qSort(ints);
        System.out.println(Arrays.toString(ints));

    }



    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Test
    public void doTest01() {

        String msg = "appId=Y0000109&channelId=0625023500&businessId=20220408000000502&transactionId=&amount=525.0&status=90&msg=JKT IDN";

        String[] array = msg.split("&");
        Map<String, Object> input = new HashMap<String, Object>();
        for(String str: array) {
            String[] s = str.split("=");
            input.put(s[0], s[1]);
        }

    }

}
