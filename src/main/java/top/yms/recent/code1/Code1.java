package top.yms.recent.code1;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Code1 {

    static class A {
        public static void fun() {
            System.out.println("hello world");
        }
        public void fun1() {
            System.out.println("fun1");
        }
    }

    static class B extends A{
        public static void fun() {
            System.out.println("waht B");
        }
        public void fun1() {
            System.out.println("fun2");
        }
    }

    public void swap(int [] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public void quickSort(int [] arr) {
        if (arr == null) return;
        if (arr.length == 1) return;

        int len = arr.length;
        quick_sort(arr, 0, len-1);
    }
    public void quick_sort(int [] arr, int i, int j) {
        if (i<j) {
            int r = partition(arr, i, j);
            quick_sort(arr, i, r-1);
            quick_sort(arr,r+1, j);
        }

    }

    public int partition(int [] arr, int i, int r) {
        int polot = arr[r];

        for (int j = i; j < r; j++) {
            if (arr[j] < polot) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr,i,r);
        return i;
    }


    private Random random = new Random();

    public synchronized int random() {
        int min = 0;
        int max = 10000;

        return new Random().nextInt(max-min)+min;
    }


    public static void main1(String[] args) throws Exception {
        Code1 code1 = new Code1();

        int n = 10;

        long startTime=System.currentTimeMillis();   //获取开始时间
        int [] arr = new int[n];
        long endTime=System.currentTimeMillis(); //获取结束时间
        double useTime = (double)((endTime-startTime)/1000.0);
        System.out.println("分配O("+n+")空间用时:"+useTime+"/s");

        startTime=System.currentTimeMillis();   //获取开始时间
        for (int i = 0; i < n; i++) {
            int v = code1.random();
            arr[i] = v;
        }
        endTime=System.currentTimeMillis(); //获取结束时间
        useTime = (double)((endTime-startTime)/1000.0);
        System.out.println("填充O("+n+")空间用时:"+useTime+"/s");

        startTime=System.currentTimeMillis();   //获取开始时间
        code1.mergeSort(arr);
        endTime=System.currentTimeMillis(); //获取结束时间
        useTime = (double)((endTime-startTime)/1000.0);
        System.out.println("排序O("+n+")空间用时:"+useTime+"/s");

       System.out.println(Arrays.toString(arr));

    }


    public static void main(String[] args) {
        int [] nums = {7,5,6,4};
        Code1 code1 = new Code1();
        code1.mergeSort(nums);
        System.out.println(Arrays.toString(nums));
        System.out.println("double="+code1.resDouble);
    }

    public void mergeSort(int [] arr) {
        if (arr == null) return;
        if(arr.length <2) return;
        int len = arr.length;

        int [] tmp = new int[len];
        mergeSort(arr,0,len-1,tmp);
    }
    public void mergeSort(int [] arr, int i, int j, int [] tmp) {
        if (i<j) {
            int mid = (i+j)/2;
            mergeSort(arr,i,mid,tmp);
            mergeSort(arr,mid+1, j, tmp);
            merge(arr,i,mid,j,tmp);
        }
    }


    int resDouble = 0;

    public void merge(int [] arr, int left, int mid, int right, int [] tmp) {
        int i=left;
        int j = mid +1;
        int t = 0;
        while (i<=mid && j <= right) {
            if (arr[i] < arr[j]) {
                tmp[t++] = arr[i++];
                resDouble++;
            } else {
                tmp[t++] = arr[j++];
            }
        }
        while (i<=mid) {
            tmp[t++] = arr[i++];
        }
        while (j<=right) {
            tmp[t++] = arr[j++];
        }

        t = 0;
        while (left<=right) {
            arr[left++] = tmp[t++];
        }
    }






}
