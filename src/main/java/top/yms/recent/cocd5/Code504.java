package top.yms.recent.cocd5;

public class Code504 {

    /**
     * MergeSort 实现
     * @param arr
     */
    public void mSort(int [] arr) {
        if (arr == null) return;
        int len = arr.length;
        if (len < 2) return;
        int [] tmp = new int[len];

        mSort(arr,0, len-1, tmp);
    }

    private void mSort(int [] arr, int left, int right, int [] tmp) {
        if (left < right) {
            int mid = (left+right)>> 1;
            mSort(arr, left, mid, tmp);
            mSort(arr, mid+1, right, tmp);
            merge(arr, left, mid, right, tmp);
        }
    }

    private void merge(int [] arr, int left, int mid, int right, int [] tmp) {
        int i = left;
        int j = mid+1;
        int t = 0;
        while (i <=mid && j <= right) {
            if (arr[i] < arr[j]) {
                tmp[t++] = arr[i++];
            } else {
                tmp[t++] = arr[j++];
            }
        }

        while (i<=mid) tmp[t++] = arr[i++];
        while (j<=right) tmp[t++] = arr[j++];
        t = 0;

        while (left <= right) arr[left++] = tmp[t++];
    }


    public static void print(int [] arr, int n) {
        for(int i=0; i<n; i++) {
            System.out.print(arr[i]+", ");
        }
        System.out.println();
    }

    private void merG(int [] arr, int l, int m, int r, int [] tmp) {
        int t =0;
        int left = l;
        int mid = m+1;
        int right = r;

        while (left<=mid && mid <= right) {
            if(arr[left] < arr[right]) {
                tmp[t++] = arr[left++];
            } else {
                tmp[t++] = arr[right++];
            }
        }

        while (left <= mid) tmp[t++] = arr[left++];
        while (mid <= right) tmp[t++] = arr[right++];

        t = 0;
        while (l <= r) {
            arr[l++] = tmp[t++];
        }

    }


    public static void main(String[] args) {
         int [] arr = {7,8,3,4,9,0,5};

        Code504 code504 = new Code504();
        code504.mSort(arr);
        print(arr, arr.length);

    }



}
