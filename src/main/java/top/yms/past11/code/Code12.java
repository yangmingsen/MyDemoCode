package top.yms.past11.code;

public class Code12 {

    int recursion(int [] arr, int i, int len) {
        if (i >= len) {
            return 0;
        }

        return Math.max(recursion(arr, i+2, len) + arr[i], recursion(arr, i+1, len));
    }

    int fun(int [] arr, int len) {
        int [] dp = new int[len];
        dp[0] = arr[0];
        for (int i = 1; i < len; i++) {
            dp[i] = Math.max(dp[i-1]+arr[i], arr[i]);
        }

        return dp[len-1];
    }


    int fun2(int [] arr, int len) {
        int dp0 = 0;
        int dp1 = arr[0];

        for (int i = 1; i < len; i++) {
            int t = dp0;
            dp0 = Math.max(dp0, dp1);
            dp1 = t + arr[i];
        }

        return Math.max(dp0,dp1);
    }
    public int massage(int[] nums) {
        int size = nums.length;
        if (size == 0) {
            return 0;
        }
        int dp0 = 0;		// dp[0][0] = 0;
        int dp1 = nums[0];	// dp[0][1] = nums[0];
        for (int i = 1; i < size; i++) {
            int temp = dp0;
            dp0 = Math.max(dp0, dp1);	//dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
            dp1 = temp + nums[i];		//dp[i][1] = dp[i-1][0] + nums[i];
        }
        return Math.max(dp0, dp1);
    }



    public static void main(String[] args) {
        int [] arr = {3,1,7};
        int len = arr.length;
        Code12 code12 = new Code12();
        System.out.println(code12.recursion(arr,0, len));
        System.out.println(code12.fun(arr, len));
        System.out.println(code12.fun2(arr, len));
        System.out.println(code12.massage(arr));

    }

}
