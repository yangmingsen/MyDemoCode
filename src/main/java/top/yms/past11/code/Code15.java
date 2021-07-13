package top.yms.past11.code;

import java.util.HashMap;
import java.util.Map;

public class Code15 {

    Map<Integer,Integer> map = null;
    public int waysToStep(int n) {
         map = new HashMap<>();
         return fun(n);
    }

    public int fun(int n) {
        if (n==0) return 1;
        if (n<0) return 0;
        if (map.get(n) != null) {
            return map.get(n);
        }
        int res1 = (fun(n-1)+fun(n-2))%1000000007;
        int res2 = (res1+fun(n-3)) %1000000007;
        map.put(n, res2);
        return res2;
    }

    public int fun2(int n) {
        if (n==0) return 1;
        if (n<0) return 0;
        int res1 = (fun2(n-1)+fun2(n-2))%1000000007;
        int res2 = (res1+fun2(n-3)) %1000000007;
        return res2;
    }



    public int dpFunc(int n) {
        int [] dp = {0,1,2,4};
        if (n <=3 ) return dp[n];

        for (int i = 4; i <=n; i++) {
            int r1 = (dp[1]+dp[2])%1000000007;
            int r2 = (r1+dp[3])%1000000007;
            for (int j = 1; j < 3; j++) {
                dp[j] = dp[j+1];
            }
            dp[3]=r2;
        }
        return dp[3];
    }

    public int dpFun(int n) {
        int res = 0;
        if (n==1)return 1;
        if (n==2) return 2;
        if (n==3) return 4;

        int dp1 = 1;
        int dp2 = 2;
        int dp3 = 4;

        for (int i = 4; i <=n; i++) {
            int r1 = (dp1+dp2)%1000000007;
            res = (r1+dp3)%1000000007;
            int t = dp3;
            dp3 = res;

            int t2 = dp2;
            dp2 = t;

            dp1 = t2;
        }

        return res;
    }

    public static void main(String[] args) {
        Code15 code15 = new Code15();
        int x = 35;

        long startTime=System.currentTimeMillis();   //获取开始时间
        int res = code15.dpFun(x);
        long endTime=System.currentTimeMillis(); //获取结束时间
        System.out.println(res);
        System.out.println("程序运行时间： "+(double)((endTime-startTime)/1000.0)+"s");

        startTime=System.currentTimeMillis();
        res = code15.dpFunc(x);
        endTime=System.currentTimeMillis(); //获取结束时间
        System.out.println(res);
        System.out.println("程序运行时间： "+(double)((endTime-startTime)/1000.0)+"s");

        startTime=System.currentTimeMillis();
        res = code15.waysToStep(x);
        endTime=System.currentTimeMillis(); //获取结束时间
        System.out.println(res);
        System.out.println("程序运行时间： "+(double)((endTime-startTime)/1000.0)+"s");

        startTime=System.currentTimeMillis();
        res = code15.fun2(x);
        endTime=System.currentTimeMillis(); //获取结束时间
        System.out.println(res);
        System.out.println("程序运行时间： "+(double)((endTime-startTime)/1000.0)+"s");

    }




}
