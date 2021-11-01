package top.yms.recent.c202110;

import java.util.HashMap;
import java.util.Map;

public class Code202110291456 {
    // fn = f(n-1)+1 ; n=1 => 1

    public int rf(int n) {
        if (n == 1) return 1;
        return rf(n-1) +1;
    }

    // fn = 1+f(k+1) ; k=1, k<=n;
    public int df(int n) {
        int [] nums = new int[n+1];
        nums[1] = 1;
        for(int i=2; i<=n; i++) {
            nums[i] = nums[i-1]+1;
        }

        return nums[n];
    }

    public static void main(String[] args) {
        Code202110291456 code = new Code202110291456();

        int n = 145;
        System.out.println(code.jumpDf(n));
        System.out.println(code.jumpRfCut(n));
        System.out.println(code.jumpRf(n));


    }

    //剪枝 递归
    Map<Integer, Integer> map = new HashMap<>();
    public int jumpRfCut(int n) {
        if (n == 1 ) return 1;
        if (n == 2) return 2;
        if (map.get(n) != null) return map.get(n);

        int x = jumpRfCut(n-1);
        map.put(n-1, x);

        int y = jumpRfCut(n-2);
        map.put(n-2, y);

        map.put(n, x+y);
        return x+y;
    }

    //fn = f(n-1) + f(n-2); n==1 => 1, n==2 => 2
    public int jumpRf(int n) {
        if (n == 1 ) return 1;
        if (n == 2) return 2;

        return jumpRf(n-1) + jumpRf(n-2);
    }

    //dn = d(i+1) +d(i+2),i<=n;
    public int jumpDf(int n) {
        if (n == 1 ) return 1;
        if (n == 2) return 2;

        int [] nums = new int[n+1];
        nums[1] = 1;
        nums[2] = 2;

        for(int i=3; i<=n; i++) {
            nums[i] = nums[i-1]+nums[i-2];
        }
        return nums[n];
    }


}
