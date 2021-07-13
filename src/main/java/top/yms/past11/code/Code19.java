package top.yms.past11.code;

public class Code19 {
    int [] weight = {4,5,2,1,2};
    int [] value = {50, 40, 10, 20, 40};
    int n = 10;
    int len = weight.length;

    int doRec(int cw, int i) {
        if (cw == n) return 0;
        if (cw > n) return Integer.MIN_VALUE/2;
        if (i >= len) return Integer.MIN_VALUE/2;

        int x = doRec(cw+weight[i], i)+value[i];
        int y =doRec(cw,i+1);

        return Math.max(x,y);
    }

    int dpFun() {
        int [][] dp = new int[n][n+1];

        for (int i = weight[0]; i <= n; i++) {
            dp[0][i] = dp[0][i-weight[0]] + value[0];
        }

        for (int i = 1; i < len; i++) {
            for (int j = 1; j <=n; j++) {
                int x = dp[i-1][j];
                int y = j-weight[i] >=0 ? dp[i][j-weight[i]]+value[i] : 0;
                dp[i][j] = Math.max(x,y);
            }
        }

        return dp[len-1][n];
    }

    public static void main(String[] args) {
        Code19 code19 = new Code19();
        System.out.println(code19.dpFun());
        System.out.println(code19.doRec(0,0));
    }


}
