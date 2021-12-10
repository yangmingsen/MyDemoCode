package top.yms.recent.c202111;

import org.junit.jupiter.api.Test;

//地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一
//格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但
//它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
//
//
//
// 示例 1：
//
// 输入：m = 2, n = 3, k = 1
//输出：3
//
//
// 示例 2：
//
// 输入：m = 3, n = 1, k = 0
//输出：1
//
//
// 提示：
//
//
// 1 <= n,m <= 100
// 0 <= k <= 20
//
public class MachineRunRange {

    @Test
    public void test()  {

        int m = 2,n=3,k=1;
        System.out.println("3="+movingCount(m,n,k));
        m=3;n=1;k=0;
        System.out.println("1="+movingCount(m,n,k));

       m=16;n=8;k=4;
        System.out.println(movingCount(m,n,k));


    }


    @Test
    public void test02() {
        System.out.println(fun(35));
        System.out.println(fun(37));
    }



    public int movingCount1(int m, int n, int k) {
        int res = 0;
        for(int i=0; i<m; i++) {
            if (i > k) break;
            for(int j=0; j<n; j++) {
                if (fun(i)+fun(j) > k) break;
                res++;
            }

        }

        return res;
    }

    private int fun(int num) {
        if (num < 10) return num;

        int total = 0;
        while (num != 0) {
            total += num%10;
            num/=10;
        }
        return total;

    }

    byte[][] map = null;
    int res=0;
    private void init(int m, int n, int k) {
        map = new byte[m][n];
        res = 0;
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                map[i][j] = 0;
            }
        }
    }

    private void doDFS(int i, int j, int m, int n, int k) {
        if (i>=0 && i<m && j>=0 && j<n && (fun(i)+fun(j) <= k) && map[i][j]==0) {
            map[i][j] = 1;
            res++;

            doDFS(i+1,j,m,n,k);
            doDFS(i-1,j,m,n,k);
            doDFS(i,j+1,m,n,k);
            doDFS(i,j-1,m,n,k);
        }
    }

    public int movingCount(int m, int n, int k) {
        init(m,n,k);
        doDFS(0,0,m,n,k);
        return res;
    }

}
