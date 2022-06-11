package top.yms.recent.c202110;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Code20220124 {



    static class MedianFinder {
        public static void swap(int [] arr, int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }

        private int partition(int [] arrs, int l, int r) {
            int pivot = arrs[r];
            for(int i = l; i<r; i++) {
                if (arrs[i] > pivot) {
                    swap(arrs, l++, i);
                }
            }
            swap(arrs, l, r);

            return l;
        }

        private void quickSortPartition(int [] arr, int l, int r) {
            if (l < r) {
                int q = partition(arr, l, r);
                quickSortPartition(arr, l, q-1);
                quickSortPartition(arr, q+1, r);
            }
        }


        private int size;
        private int [] data = null;
        private int Max = 50000;
        private int rCmdSize = 0;

        /** initialize your data structure here. */
        public MedianFinder() {
            size=0;
            data = new int[Max];
            rCmdSize++;
        }

        public void addNum(int num) {
            data[size++]=num;
            quickSortPartition(data,0, size);
            rCmdSize++;

        }

        public double findMedian() {
            double res;
            if (rCmdSize/2 == 1) {
                res =  data[size/2]*1.0d;
            } else {
                int f1 = size/2;
                int f0 = f1 - 1;
                res =  (data[f0]+data[f1])/2.0d;
            }
            rCmdSize++;
            return res;
        }
    }

 // 示例 1：
//
// 输入：
//["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
//[[],[1],[2],[],[3],[]]
//输出：[null,null,null,1.50000,null,2.00000]
//
//
// 示例 2：
//
// 输入：
//["MedianFinder","addNum","findMedian","addNum","findMedian"]
//[[],[2],[],[3],[]]
//输出：[null,null,2.00000,null,2.50000]
//


    private static class Case {
        String cmd;
        int data;

        public Case(String cmd, int data) {
            this.cmd = cmd;
            this.data = data;
        }

        public Case(String cmd) {
            this.cmd = cmd;
        }
    }

    private void simulateComp(List<Case> exeL ) {

        MedianFinder medianFinder = null;
        for (Case ex : exeL) {
            switch (ex.cmd) {
                case "MedianFinder": medianFinder = new MedianFinder();break;
                case "addNum": medianFinder.addNum(ex.data);break;
                case "findMedian":
                    System.out.print(medianFinder.findMedian()+",");
            }
        }
        System.out.println();
    }

    private List<Case> init() {
        return new ArrayList<Case>() {{
           add(new Case("MedianFinder")) ;
           add(new Case("addNum",1)) ;
           add(new Case("addNum",2)) ;
           add(new Case("findMedian")) ;
           add(new Case("addNum",3)) ;
           add(new Case("findMedian")) ;
        }};



    }

    @Test
    public void test() {
        List<Case> init = init();
        simulateComp(init);
    }

}
