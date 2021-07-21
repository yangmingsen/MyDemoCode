package top.yms.recent.c202101_07.cocd5;

import static top.yms.past11.solution.Solution.swap;

public class BinarySearhO {

    /**
     * 查找第一个值等于给定值的元素的下标
     * @param arr
     * @param n
     * @param searchV
     * @return
     */
    public static int bianrySearch1(int [] arr, int n, int searchV) {
        int low = 0;
        int high = n-1;
        while (low<=high) {
            int mid = low+((high-low) >> 1);
            if (searchV < arr[mid]) {
                high = mid-1;
            } else if (searchV > arr[mid]) {
                low = mid+1;
            } else {
                if (low == 0 || arr[mid-1] != searchV) return mid;
                else high = mid-1;
            }
        }
        return -1;
    }

    /**
     * 查找最后一个值等于给定值的元素
     * @param arr
     * @param n
     * @param searchV
     * @return
     */
    public static int bianrySearch2(int [] arr, int n, int searchV) {
        int low = 0;
        int high = n-1;
        while (low<=high) {
            int mid = low+((high-low) >> 1);
            if (searchV < arr[mid]) {
                high = mid-1;
            } else if (searchV > arr[mid]) {
                low = mid+1;
            } else {
                if (high == n-1 || arr[mid+1] != searchV) return mid;
                else low = mid+1;
            }
        }
        return -1;
    }


    /**
     * 查找第一个值等于给定值的元素的下标
     * @param arr
     * @param n
     * @param searchV
     * @return
     */
    public static int bianrySearch3(int [] arr, int n, int searchV) {
        int low = 0;
        int high = n-1;
        while (low<=high) {
            int mid = low+((high-low) >> 1);
            if (searchV < arr[mid]) {
                high = mid-1;
            } else if (searchV > arr[mid]) {
                low = mid+1;
            } else {
                if (low == 0 || arr[mid-1] < searchV) return mid;
                else high = mid-1;
            }
        }
        return -1;
    }


    /**
     * 查找最后一个值小于等于给定值的元素
     * @param arr
     * @param n
     * @param searchV
     * @return
     */
    public static int bianrySearch4(int [] arr, int n, int searchV) {
        int low = 0;
        int high = n-1;
        while (low<=high) {
            int mid = low+((high-low) >> 1);
            if (searchV < arr[mid]) {
                high = mid-1;
            } else if (searchV > arr[mid]) {
                low = mid+1;
            } else {
                if (high == n-1 || arr[mid+1] > searchV) return mid;
                else low = mid+1;
            }
        }
        return -1;
    }


    public static void print(int [] arr, int n) {
        for(int i=0; i<n; i++) {
            System.out.print(arr[i]+", ");
        }
        System.out.println();
    }


    public static void insertionSort(int [] arr, int n) {
        if (n <= 1) return;

        for(int i=1; i<n; i++) {
            int value = arr[i]; //需要插入的值
            int j = i-1; //上一个元素位置
            for(; j>=0; j--) {
                if (arr[j] > value) {
                    arr[j+1] = arr[j];//移动位置
                } else {
                    break;
                }
            }
            arr[j+1] = value;// 插入数据
        }

    }

    public static void selectionSort(int [] arr, int n) {
        for(int i=0; i<n; i++) {
            int minIdx = i;
            for(int j=i+1; j<n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }

            if (i != minIdx) {
                swap(arr,i, minIdx);
            }

        }
    }



    private  static int partition(int [] arr, int l, int r){
        int pivot = arr[r];
        for (int i = l; i < r; i++) {
            if (arr[i] < pivot) {
                swap(arr,l,i);
                l++;
            }
        }
        swap(arr,l,r);
        return l;
    }

    public static void quicksort(int [] arr) {
        if (arr == null) return;;
        int len = arr.length;
        if (len <2) return;
        quicksort(arr,len-1);
    }
    public static void quicksort(int [] arr, int len) {
        quicksort(arr,0,len);
    }

    private static void quicksort(int [] arr, int l, int r) {
        //if (l >= r) return;
        if (l < r) {
            int mm = partition2(arr,l,r);
            quicksort(arr,l,mm-1);
            quicksort(arr,mm,r);
        }
    }



    public static void main(String[] args) {
        int [] arr = {7,8,3,4,9,0,5};
        BinarySearhO binarySearhO = new BinarySearhO();
        quicksort(arr);
        print(arr,arr.length);

    }

    private static int partition2(int [] arr, int l, int r) {
        int poivot = arr[r];
        for (int i = l; i < r; i++) {
            if(arr[i] < poivot) {
                swap(arr, l, i);
                l++;
            }
        }
        swap(arr, l, r);

        return l;
    }


}