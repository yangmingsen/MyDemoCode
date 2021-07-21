package top.yms.recent.c202101_07.code6;

import java.util.Arrays;

public class Code602 {

    static int[][] testDemo() {
        int [][] demo = new int[][] {
                null,
                {},
                {2,1},
                {1,5,3},
                {1,1,1,1,1,1,1,1,1,1},
                {1,1,1,2,2,2,2,3,3,3},
                {4,2,3,7,0,5,6,8,1,9}
        };

        return demo;
    }

    static int [] getResult(int [] arr, int minK) {
        int [] tmpR = new int[0];
        if (arr == null) return tmpR;
        int len = arr.length;
        if (minK < 1 || len < 1) return tmpR;
        if (len <= minK) return arr;

        int [] res = new int[minK];
        Arrays.sort(arr);
        for(int i=0; i<minK; i++) res[i] = arr[i];
        return res;
    }

    public int[] getLeastNumbers(int[] arr, int k) {
        return getResult(arr,k);
    }

    public static void main(String[] args) {
        int[][] testData = testDemo();
        int row = testData.length;
        for(int i=0; i<row; i++) {
            int[] result = getResult(testData[i], 3);
            if (result == null) {
                System.out.println("row["+i+"]=null");
            } else {
                System.out.println("row["+i+"]="+Arrays.toString(result));
            }
        }

    }

}
