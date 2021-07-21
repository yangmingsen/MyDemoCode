package top.yms.recent.c202101_07.code5;

import top.yms.past11.solu.TreeNode;

public class Code501 {




    public static void dpSum(int [] nums) {
        int maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            nums[i] = Math.max(nums[i]+nums[i-1], nums[i]);
            maxSum = Math.max(maxSum, nums[i]);
        }
        System.out.println(maxSum);
    }

    public int maxSubArray(int[] nums) {
        return violenceCpt(nums);
    }

    public static int violenceCpt(int [] nums) {
        int maxSum = Integer.MIN_VALUE;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int tmpSum = 0;
            for (int j=i; j<len; j++) {
                tmpSum+=nums[j];
                if (maxSum < tmpSum) {
                    maxSum = tmpSum;
                }
            }
        }
        return maxSum;
    }


    int maxDep(TreeNode tree) {
        if (tree == null) return 0;

        int left = maxDep(tree.left);
        int right = maxDep(tree.right);

        return Math.max(left,right)+1;

    }

    public static void main(String[] args) {
        String str = "123";
        System.out.println(str.compareTo("234"));

    }



}
