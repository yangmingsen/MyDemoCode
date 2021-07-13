package top.yms.recent.code8;

public class Code813 {

    //一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
//
// 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
//
// 示例 1：
//
// 输入：n = 2
//输出：2
//
//
// 示例 2：
//
// 输入：n = 7
//输出：21
//
//
// 示例 3：
//
// 输入：n = 0
//输出：1
//
// 提示：
//
//
// 0 <= n <= 100
//
//
    public int numWays(int n) {
        if (n < 1) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;

        int [] dp = new int[n+1];
        dp[1] = 1;dp[2]=2;
        for(int i=3; i<=n; i++) {
            dp[i] = (dp[i-1]+dp[i-2])%0x3B9ACA08;
        }

        return dp[n];
    }

    //输入：
    //44
    //输出：
    //134903162
    //预期结果：
    //1134903170

    public int climbStairs(int n) {
        if (n < 1) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;

        int [] dp = new int[n+1];
        dp[1] = 1;dp[2]=2;
        for(int i=3; i<=n; i++) {
            dp[i] = (dp[i-1]+dp[i-2]);
        }

        return dp[n];
    }


    public static void main(String[] args) {
        Code813 code813 = new Code813();

        for(int i=44; i<45; i++) {
            System.out.println(code813.climbStairs(i));
        }


    }

}
