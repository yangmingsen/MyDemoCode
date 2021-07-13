package top.yms.recent.code9;

import java.util.Arrays;

public class Code903 {

    private static void print(int [] arr) {
        int len = arr.length;
        for(int i=0; i<len; i++) {
            System.out.print(arr[i]);
        }
        System.out.println();
    }

    private static void doComp(int [] arr, int n, int idx) {
        if (idx == n) {
            print(arr);
            return;
        }

        for(int i=0; i<10; i++) {
            arr[idx] = i;
            doComp(arr, n, idx+1);
        }
    }

    private static void comp(int n) {
        if (n <= 0) {
            return;
        }
        int [] arr = new int [n];
        doComp(arr, n, 0);

    }

    /**
     * 递归处理
     * @param nums 数值数组
     * @param len 数值位数长度
     * @param indx 开始点
     */
    private static void doRec(int [] nums, int len, int indx) {
        if ((len) == indx) {
            System.out.println(Arrays.toString(nums));
            return;
        }
        for(int i=0; i<10; i++) {
            nums[indx] = i;
            doRec(nums, len, indx+1);
        }

    }

    /**
     * 数值位数
     * @param n
     */
    public static void myRec(int n) {
        int [] nums = new int[n];
        doRec(nums, n, 0);
    }


    public static void main(String[] args) {
        comp(70);
    }
}
