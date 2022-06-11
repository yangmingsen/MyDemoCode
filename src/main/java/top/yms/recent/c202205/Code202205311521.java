package top.yms.recent.c202205;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Code202205311521 {

    @Test
    public void test1550() {
        int [] tc = {-1,2,1,-4};
        int tg = 1;

        System.out.println(        threeSumClosest(tc, tg));


        int [] tc1 = {0,0,0};
        int tg1 = 1;
        System.out.println(threeSumClosest(tc1, tg1));

        int [] tc2 = {-1,2,1,-4,3};
        int tg2 = 1;
        System.out.println(threeSumClosest(tc2, tg2));
    }

    public int threeSumClosest(int[] nums, int target) {
        int res = 0;

        int nLen = nums.length;
        if (nLen == 3) return sumi(nums, 0, 3);

        int threeSum = nums[0]+nums[1]+nums[2];



        return res;

    }


    public int sumi(int[] nums, int i, int j) {
        int s = 0;
        for (; i < j; i++) {
            s += nums[i];
        }
        return s;
    }

    public int threeSumClosest1(int[] nums, int target) {
        int res = -1;

        int nLen = nums.length;
        if (nLen == 3) return sumi(nums, 0, 3);

        Arrays.sort(nums);
        int[] sums = new int[nLen - 1];
        for (int i = 0; i < nLen - 2; i++) {
            sums[i] = sumi(nums, i, i + 3);
        }
        sums[nLen - 2] = target;

        Arrays.sort(sums);
        for (int i = 0; i < nLen - 1; i++) {
            if (sums[i] == target) {
                if (i == 0) { //左开
                    res = sums[i + 1];
                } else if (i == nLen - 2) {
                    //right
                    res = sums[i - 1];
                } else {
                    int le = Math.abs(sums[i - 1]);
                    int ri = Math.abs(sums[i + 1]);
                    int lec = le > target ? le - target : target - le;
                    int ric = ri > target ? ri - target : target - ri;

                    res = lec < ric ? sums[i - 1] : sums[i + 1];
                }
                break;
            }
        }


        return res;
    }
}
