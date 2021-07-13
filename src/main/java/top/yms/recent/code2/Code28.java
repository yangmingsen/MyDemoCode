package top.yms.recent.code2;

public class Code28 {

    /**
     *  完全背包 动态规划版
     *
     * @param weight 重量
     * @param value 价值
     * @param knapsackCap 背包容量
     * @return 最大可以获取的价值
     */
    int dpFun(int [] weight, int [] value, int knapsackCap) {
        int theGoodsNum = weight.length;

        int [][] dp = new int[theGoodsNum][knapsackCap+1];
        for (int i = 1; i <= knapsackCap; i++) {
            if (weight[0] <= i) {
                dp[0][i] = dp[0][i-weight[0]]+value[0];
            }
        }

        for (int i = 1; i < theGoodsNum; i++) {
            for (int j = 1; j <=knapsackCap ; j++) {
                if(weight[i] <= j) {
                    dp[i][j] = Math.max(dp[i][j-weight[i]]+value[i], dp[i-1][j]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[theGoodsNum-1][knapsackCap];
    }

    public static void main(String[] args) {
        int [] weight = {4,5,2,1,2,4}; //重量
        int [] value = {50, 40, 60, 20, 30, 23}; //价值
        int knapsackCap = 10; //背包容量

        Code28 code28 = new Code28();
        System.out.println(code28.dpFun(weight,value,knapsackCap));

    }

}
