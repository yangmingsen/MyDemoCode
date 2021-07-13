package top.yms.past11.solution;

import java.util.HashMap;
import java.util.Map;

public class Solution2 {
    public int cuttingRope1(int n) {
        int maxNum = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            int n1 = n/i;
            int n2 = n%i;
            int [] tmp = new int[n1+1];
            for (int j = 0; j < n1; j++) {
                tmp[j] = i;
            }
            tmp[n1]=n2;
            if (n1 == 1) n2 = 0;

            int sum1 = 1;
            for (int j = 0; j < n1-1; j++) {
                sum1=(sum1*tmp[j])%1000000007;
            }

            if (n2 != 0) {
                int x = (sum1*(tmp[n1-1]+tmp[n1]))%1000000007;
                int y = (sum1*((tmp[n1-1]*tmp[n1])))%1000000007;
                sum1 = Math.max(x,y);
            } else {
                sum1 = (sum1*tmp[n1-1])%1000000007;
            }

            if (sum1 > maxNum) {
                maxNum = sum1;
            }
        }
        return maxNum;
    }

    public int doRec(int n) {
        if (n==1 || n==0) return 1;
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            int a = (doRec(n-i)*i)%1000000007;
            if (a>max) max = a; else break;
        }
        return max;
    }

    Map<Integer, Integer> map = new HashMap<>();
    public int doFun(int n) {
        int max = Integer.MIN_VALUE;
        for (int i = 1 ; i < n; i++) {
            int cc = (i*doRec(n-i))%1000000007;
            if (cc>max) max = cc; else break;
        }
        return max;
    }

    public int mathFun(int n) {
        //n = 3^a+b
        if (n<=3) return n-1;
        int a = n/3;
        int b = n%3;
        if (b==0) return (int)Math.pow(3,a);
        if (b==1) return (int)Math.pow(3,a-1)*4;
        return (int)Math.pow(3,a)*2;
    }

    public int mathFun2(int n) {
        //n = 3^a+b
        if (n<=3) return n-1;
        int a = n/3;
        int b = n%3;
        if (b==0) return pow(3,a);
        if (b==1) return pow(3,a-1)*4 %1000000007;
        return pow(3,a)*2%1000000007;
    }

    public int pow(int num, int n) {
        if (n==0) return 1;
        int ans = num;
        for (int i = 1; i < n; i++) {
            ans = (ans*num)%1000000007;
        }
        return ans;
    }

    public int quickPow(int a, int b, int c) {
        a %= c;
        int ans = 1;
        while (b>0) {
            if( (b & 1) !=0) ans = (ans * a)%c;
            a = (a*a)%c;
            b >>=1;
        }
        return ans;
    }



    public int cuttingRope(int n) {
        return mathFun2(n);
    }


    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        for (int i = 0; i < 10; i++) {
            System.out.println(solution2.quickPow(2,i,1));
        }
    }


    public static void main4(String[] args) {
        Solution2 solution2 = new Solution2();

        System.out.println(solution2.cuttingRope(120));
    }

    public static void main1(String[] args) {
        Solution2 solution2 = new Solution2();
        System.out.println(solution2.doFun(2));
        System.out.println(solution2.doFun(3));
        System.out.println(solution2.doFun(4));
        System.out.println(solution2.doFun(5));
        System.out.println(solution2.doFun(10));
        System.out.println(solution2.doFun(100));
    }

}
