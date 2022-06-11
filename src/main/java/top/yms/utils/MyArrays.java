package top.yms.utils;


import java.util.Arrays;

public class MyArrays {

    public static void swap(char [] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void swap(int [] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static int ipvot(int [] arr, int l, int r) {
        int pivot = arr[r];
        for(int i=l; i<r; i++) {
            if (pivot > arr[i]) {
                swap(arr, l++, i);
            }
        }
        swap(arr, l, r);

        return l;
    }

    private static void qSort(int [] arr, int l, int r) {
        if (l < r) {
            int p = ipvot(arr, l, r);
            qSort(arr, l, p-1);
            qSort(arr, p+1, r);
        }
    }

    public static void qSort(int [] arr) {
        if (arr == null) return;
        int r = arr.length;
        if (r == 1) return;
        qSort(arr, 0, r-1);
    }

    public static void print(int [] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static void print(char [] arr) {
        System.out.println(Arrays.toString(arr));
    }
}
