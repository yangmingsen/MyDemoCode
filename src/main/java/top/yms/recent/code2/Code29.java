package top.yms.recent.code2;

import java.util.Arrays;

public class Code29 {
    int coinsChange(int [] coins, int num) {
        int maxV = num+1;
        int [] dp = new int[maxV];
        Arrays.fill(dp, maxV);
        dp[0] = 0;

        for (int i = 1; i <= num; i++) {
            for (int j = 0; j < coins.length; j++) {
                if(coins[j] <=i) {
                    dp[i] = Math.min(dp[i], dp[i-coins[j]]+1);
                }
            }
        }

        return dp[num] == maxV ? -1 : dp[num];
    }

    public static void main(String[] args) {
        Code29 code29 = new Code29();
        int [] coins = {1,2,5};
        int num = 11;
        System.out.println(code29.coinsChange(coins,num));
    }
}
