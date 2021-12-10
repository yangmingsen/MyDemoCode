package top.yms.recent.c202111;

import org.junit.jupiter.api.Test;

//把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
// 输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2]
// 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。
//
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
public class RotateMinNumber {

    public int minArray(int[] numbers) {
        if (numbers == null) return  -1;

        int low = 0;
        int high = numbers.length - 1;
        while (low < high) {
            int pivot = (low + high)/2;
            if (numbers[pivot] < numbers[high]) {
                high = pivot ;
            } else if (numbers[pivot] > numbers[high]) {
                low = pivot + 1;
            } else {
                high--;
            }
        }
        return numbers[low];
    }

    @Test
    public void test() {
        int [] case1 =  {3,4,5,1,2};
        int [] case2 =  {1,2,3,4,5};
        int [] case3 =  {2,2,2,0,1};
        int [] case4 =  {3,1,3};

        System.out.println("c1="+minArray(case1));
        System.out.println("c2="+minArray(case2));
        System.out.println("c3="+minArray(case3));
        System.out.println("c4="+minArray(case4));

    }


}
