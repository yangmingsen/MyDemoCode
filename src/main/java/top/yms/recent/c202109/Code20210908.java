package top.yms.recent.c202109;

public class Code20210908 {

// 示例 1：
//
// 输入：[3,4,5,1,2]
//输出：1
//
//
// 示例 2：
//
// 输入：[2,2,2,0,1]
//输出：0
    public int minArray(int[] numbers) {
        int low = 0;
        int high = numbers.length - 1;
        while (low < high) {
            int pivot =  (high + low) / 2;
            if (numbers[pivot] < numbers[high]) {
                high = pivot;
            } else if (numbers[pivot] > numbers[high]) {
                low = pivot + 1;
            } else {
                high -= 1;
            }
        }
        return numbers[low];
    }

    public static void main(String[] args) {
        int [] arr = new int[] {1,2,3,4,5};
        int [] arr1 = new int[] {3,4,5,1,2};
        int [] arr2 = new int[] {2,2,2,0,1};
        Code20210908 code20210908 = new Code20210908();

        int i = code20210908.minArray(arr2);
        System.out.println(i);
    }

}
