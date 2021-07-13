package top.yms.past11.solu;

public class Solution11 {
    public int maxSubArray(int[] nums) {
        return dpFun(nums);
    }

    public int dpFun(int [] nums) {
        int maxSum = nums[0];
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            nums[i] = Math.max(nums[i]+nums[i-1], nums[i]);
            maxSum = Math.max(maxSum,nums[i]);
        }

        return maxSum;
    }

    int sum = 0;
    public int doRec(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) {
            return root.val;
        }

        int x = doRec(root.left);
        int y = doRec(root.right);

        sum+=Math.abs(x-y);
        return x+y;
    }

    //[4,2,9,3,5,null,7]
}
