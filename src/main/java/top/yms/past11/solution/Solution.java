package top.yms.past11.solution;


public class Solution {

    public int findKthLargest(int[] nums, int k) {
       return  findNMaxNumber(nums,nums.length, k);
    }

    public static void swap(int [] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static int findNMaxNumber(int [] arr, int len, int n) {
        int ans = quick_sort(arr, 0, len-1, n);
        return ans;
    }

    private static int quick_sort(int[] arr, int p, int r, int n) {
        if (p > r) return -1;
        int q = partition(arr, p, r);
        if (n == q+1){
            return arr[q];
        } else if(n > q+1) {
            return quick_sort(arr, q+1, r,n);
        } else {
            return quick_sort(arr, p, q-1, n);
        }
    }

    private static int partition(int[] arr, int p, int r) {
        int pivot = arr[r];
        int i = p;
        for(int j = p; j<r; j++) {
            if (arr[j] > pivot) {
                swap(arr,i,j);
                i++;
            }
        }
        swap(arr,i,r);
        return i;
    }


    public static void main(String[] args) {
        int [] arr = {4,2,5,12,3,13,11};
        int ans = findNMaxNumber(arr, arr.length, 3);
        System.out.println();
        System.out.println("ans="+ans);
    }
}
