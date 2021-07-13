package top.yms.recent.code2;

public class Code26 {

    public boolean canPartition(int[] nums) {
        if (nums == null) return false;

        int arrLen = nums.length;
        if (arrLen == 1) return false;

        int sumArray = 0;
        for (int i = 0; i < arrLen; i++) {
            sumArray+=nums[i];
        }

        if (sumArray%2== 1) return false; //if not doubleNumber
        int theKnapsackCapacity = sumArray/2; //halfSumArray

        int [][] dp = new int[arrLen][theKnapsackCapacity+1];

        for (int i = 1; i <= theKnapsackCapacity ; i++) {
            if (nums[0] <= i) {
                dp[0][i] = nums[0];
            }
        }

        for (int i = 1; i <arrLen; i++) {
            for (int j = 1; j <= theKnapsackCapacity; j++) {
                if (nums[i] <= j) {
                    dp[i][j] = Math.max(dp[i-1][j-nums[i]]+nums[i], dp[i-1][j]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[arrLen-1][theKnapsackCapacity] == theKnapsackCapacity;
    }



    int theKC;
    boolean theRes = false;
    public boolean canPartition2(int[] nums) {
        if (nums == null) return false;

        int arrLen = nums.length;
        if (arrLen == 1) return false;

        int sumArray = 0;
        for (int i = 0; i < arrLen; i++) {
            sumArray += nums[i];
        }

        if (sumArray % 2 == 1) return false; //if not doubleNumber
        int theKnapsackCapacity = sumArray / 2; //halfSumArray
        theKC = theKnapsackCapacity;

        return doRec(arrLen-1,0,nums)==theKC;
    }

    private int doRec(int i, int cw, int [] nums) {
        if (i<0) return 0;
        if (cw+nums[i] > theKC) return 0;
        int pick = doRec(i-1, cw+nums[i], nums)+nums[i];
        int noPick = doRec(i-1, cw, nums);
        return Math.max(pick,noPick);
    }



    public static void main(String[] args) {
        int [] arr1 = {1,5,11,5};
        int [] arr2 = {20,2,4,6};
        int [] arr3 = {1, 2, 3, 5, 5};
        int [] arr4 = {2,4};
        int [] arr5 = {3,3};

        Code26 code26 = new Code26();
        System.out.println(code26.canPartition(arr1));
        System.out.println(code26.canPartition(arr2));
        System.out.println(code26.canPartition(arr3));
        System.out.println(code26.canPartition(arr4));
        System.out.println(code26.canPartition(arr5));

        System.out.println();
        System.out.println(code26.canPartition2(arr1));
        System.out.println(code26.canPartition2(arr2));
        System.out.println(code26.canPartition2(arr3));
        System.out.println(code26.canPartition2(arr4));
        System.out.println(code26.canPartition2(arr5));

    }


}
