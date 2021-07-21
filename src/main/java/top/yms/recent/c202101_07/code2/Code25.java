package top.yms.recent.c202101_07.code2;

import java.util.HashMap;
import java.util.Map;

/**
 * 0-1背包问题
 */
public class Code25 {

    int [] weight = {4,5,2,1,2,4};
    int [] value = {50, 40, 60, 20, 30, 23};
    int n = 10;
    int len = weight.length;

    Map<String, Integer> map = new HashMap<>();

    /**
     * 记忆递归
     * @param cw
     * @param i
     * @return
     */
    int fun1(int cw, int i) {
        if (i >= len) return 0;
        if (cw+weight[i] > n) return 0;

        String str = cw+""+i;
        if (map.get(str) != null) {
            return map.get(str);
        }

        int pick = fun1(cw+weight[i], i+1)+value[i];
        map.put((cw+weight[i])+""+(i+1), pick);
        int noPick = fun1(cw, i+1);
        map.put(cw+""+(i+1), noPick);
        int max = Math.max(pick, noPick);
        return max;
    }

    /**
     * 普通递归实现
     * @param cw
     * @param i
     * @return
     */
    int fun(int cw, int i) {
        if (i >= len) return 0;
        if (cw+weight[i] > n) return 0;

        int pick = fun(cw+weight[i], i+1)+value[i];
        int noPick = fun(cw, i+1);

        return Math.max(pick, noPick);
    }

    /**
     * 动态规划 实现
     * @return
     */
    int dpFun() {
        int [][] dp = new int[len][n+1];
        for (int i = 1; i <= n; i++) {
            if (weight[0] <= i) {
                dp[0][i] = value[0];
            }
        }

        for (int i = 1; i < len; i++) {
            for (int j=1; j<=n; j++) {
                if (weight[i] <=j) {
                    dp[i][j] = Math.max(dp[i-1][j-weight[i]]+value[i], dp[i-1][j]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[len-1][n];
    }

    public static void main(String[] args) {
        Code25 code14 = new Code25();
        System.out.println(code14.fun(0,0));
        System.out.println(code14.fun1(0,0));
        System.out.println(code14.dpFun());
    }

}
