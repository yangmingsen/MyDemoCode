package top.yms.recent.c202204;

import org.junit.jupiter.api.Test;
import top.yms.utils.ListNode;
import top.yms.utils.MyArrays;

import java.util.Arrays;

public class Code20220415 {

    public int [] getArr() {
        return new int[] {-16, -10, -9, -2, 0, 3, 5, 8, 12, 14, 15, 19}; // 8
    }

    public int [] getAutoArr() {
        int [] arr = ListNode.autoGen(10, -20, 20);
        MyArrays.qSort(arr);
        MyArrays.print(arr);

        return arr;
    }

    public int [] findMinGroup(int [] arr, int num) {
        if (arr == null) return null;
        int end = arr.length;
        if (end == 1) return null;
        if (end == 2 && arr[0]+arr[1]==num) return arr;

        int start = 0; end--;
        for (; start < end; ) {
            if (arr[start] + arr[end] > num) end--;
            else if (arr[start] + arr[end] < num) start++;
            else return new int[] {arr[start], arr[end]};
        }

        return null;
    }

    @Test
    public void doTest202204151713() {

        System.out.println(Arrays.toString(findMinGroup(getArr(), 8)));

    }


    @Test
    public void test202204151746() {
        String str = "student. a am I";
        char[] newCode = getNewCode(str.toCharArray());
        System.out.println(newCode);
    }


    public char [] getNewCode(char [] str) {
        if (str == null) return null;

        int len = str.length;
        if (len == 1) return str;

        char [] tStr = new char[len];

        int sEnd = len-1;
        int tStart = 0;
        for(int i=sEnd; i>=0; i--) {
            if (str[i] == ' ' || i==0) {
                for(int j=i+1; j<=sEnd; j++) {
                    tStr[tStart++] = str[j];
                }
                sEnd = i-1;
            }
        }

        return tStr;

    }

}
