package top.yms.past11.code;


import java.util.Arrays;

public class Code20 {
    int n = 111;
    int [] coin = {1,3,5};
    int cLen = coin.length;

    int doRec(int cw, int i) {
        if (cw == n) return 0;
        if (cw> n) return Integer.MAX_VALUE/2;
        if (i>= cLen) return Integer.MAX_VALUE/2;

        int y = doRec(cw, i+1);
        int x = doRec(cw+coin[i], i)+1;

        return Math.min(x,y);
    }

    int dpFun() {
        int [][] dp = new int[cLen][n+1];
        for (int i = coin[0]; i <= n; i++) {
            dp[0][i] = dp[0][i-coin[0]]+1;
        }

        for (int i = 1; i < cLen; i++) {
            for (int j = 1; j <=n; j++) {
                int x = dp[i-1][j];
                int y = j >= coin[i]? dp[i][j-coin[i]]+1: 0;
                dp[i][j] = Math.min(x,y);
            }
        }

        return dp[cLen-1][n];
    }

    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        System.out.println("i="+0+" "+Arrays.toString(dp));
        System.out.println();
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                   System.out.println("i="+i+" j="+j+" "+Arrays.toString(dp));
                }
            }
            System.out.println();
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }



    public static void main(String[] args) {
        Code20 code20 = new Code20();
       // System.out.println(code20.doRec(0,0));
        //System.out.println(code20.dpFun());
        System.out.println(code20.coinChange(code20.coin, 9));
    }

}
