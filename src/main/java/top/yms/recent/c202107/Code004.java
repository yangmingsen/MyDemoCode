package top.yms.recent.c202107;

import java.util.Arrays;

public class Code004 {
    public void swap(int [] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    private int partition(int [] arrs, int l, int r) {
        int pivot = arrs[r];
        for(int i = l; i<r; i++) {
            if (arrs[i] < pivot) {
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

    public void quickSort(int [] arr) {
        quickSortPartition(arr, 0, arr.length-1);
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (m == 0 && n == 0) return;
        if (n == 0) return;
        if (m == 0) {
            for(int i=0; i<n; i++) {
                nums1[i] = nums2[i];
            }
            return;
        }
        for(int i=0; i<n; i++) {
            nums1[m+i] = nums2[i];
        }
        quickSort(nums1);
    }

    public static void main(String[] args) {
        int [] arr = {3,6,8,2,5,0,7,9,1};
        System.out.println(Arrays.toString(arr));

        Code004 code004 = new Code004();
        code004.quickSort(arr);

        System.out.println(Arrays.toString(arr));
    }
}
