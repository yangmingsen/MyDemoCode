package top.yms.recent.code7;

public class Code701 {
    //输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
    //
    //要求时间复杂度为O(n)。
    //
    //
    //
    //示例1:
    //
    //输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
    //输出: 6
    //解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
    //
    //
    //
    //提示：
    //
    //    1 <= arr.length <= 10^5
    //    -100 <= arr[i] <= 100

    public int maxSubArray(int[] nums) {
        if (nums == null) return -1;
        int numSize = nums.length;

        int maxSum = 0;
        int res = -101;

        for(int i=0; i<numSize; i++) {
            maxSum = Math.max(maxSum+nums[i], nums[i]);
            res = Math.max(res,maxSum);
        }

        return res;
    }

    public static void main(String[] args) {
        int [] nums = {-2,1,-3,4,-1,2,1,-5,4};

        String st = "草莓小姐姐\uD83C\uDF53";
        Code701 code701 = new Code701();
        int res = code701.maxSubArray(nums);

        System.out.println(res);
    }
}
