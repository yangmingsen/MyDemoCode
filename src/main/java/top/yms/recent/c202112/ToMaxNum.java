package top.yms.recent.c202112;



import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ToMaxNum {

    int [] res = null;

    public int[] printNumbers(int n) {
        if (n <=0) return res;

        int base = 1;
        for (int i=0; i<n; i++) {
            base = base*10;
        }
        res = new int[base-1];
        for (int i = 1; i < base; i++) {
            res[i-1]=i;
        }
        return res;
    }

    public void doPrint(int [] nums, int len, int idx) {
        if (len == idx) {
            System.out.println(Arrays.toString(nums));
            return;
        }

        for (int i= 0; i < 10 ; i++) {
            nums[idx] = i;
            doPrint(nums, len, idx+1);
        }
    }


    public void print(int n) {
        if (n<=0) return;

        int [] nums = new int[n];
        doPrint(nums,n, 0);
    }


    @Test
    public void testPrint() {
        print(9);
    }


    @Test
    public void doTest() {
        System.out.println(Arrays.toString(printNumbers(1)));
        System.out.println(Arrays.toString(printNumbers(2)));
    }
}
