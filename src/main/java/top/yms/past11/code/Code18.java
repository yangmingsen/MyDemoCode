package top.yms.past11.code;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Code18 {

    public int recFun(int[][] map, int i, int j) {
        int rowLen = map.length;
        int colLen = map[0].length;
        if (i + 1 == rowLen && j + 1 == colLen) {
            return map[i][j];
        }
        if (i >= rowLen | j >= colLen) return Integer.MAX_VALUE / 2;

        return Math.min(map[i][j] + recFun(map, i + 1, j), map[i][j] + recFun(map, i, j + 1));
    }

    Map<String, Integer> momery = new HashMap<>();

    public int recFun2(int[][] map, int i, int j) {
        int rowLen = map.length;
        int colLen = map[0].length;

        String key = i + "" + j;
        if (momery.get(key) != null) {
            return momery.get(key);
        }

        if (i + 1 == rowLen && j + 1 == colLen) {
            return map[i][j];
        }
        if (i >= rowLen | j >= colLen) return Integer.MAX_VALUE / 5;


        int x = recFun2(map, i + 1, j);
//        momery.put((i+1)+""+j,x);

        int y = recFun2(map, i, j + 1);
//        momery.put(i+""+(j+1), y);

        int ans = Math.min(x, y) + map[i][j];
//        System.out.println("i="+i+" j="+j+" ans="+ans);
        momery.put(key, ans);

        return ans;
    }


    public int recFunc(int [][] map, int [][] memory, int i ,int j) {
        if (i == 0 && j==0 ) return map[0][0];
        if (memory[i][j] > 0 ) return memory[i][j];
        int minLeft = Integer.MAX_VALUE;
        if (j-1 >= 0) {
            minLeft = recFunc(map,memory,i,j-1);
        }
        int minUp = Integer.MAX_VALUE;
        if (i-1 >= 0) {
            minUp = recFunc(map,memory,i-1, j);
        }

        int res = map[i][j] + Math.min(minLeft,minUp);
        memory[i][j] = res;

        return res;
    }


    public int dpFun(int[][] map) {
        int rowLen = map.length;
        int colLen = map[0].length;

        int[][] dp = new int[rowLen][colLen];
        dp[0][0] = map[0][0];
        for (int i = 1; i < colLen; i++) {
            dp[0][i] = map[0][i] + dp[0][i - 1];
        }
        for (int i = 1; i < rowLen; i++) {
            dp[i][0] = map[i][0] + dp[i - 1][0];
        }

        for (int i = 1; i < rowLen; i++) {
            for (int j = 1; j < colLen; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + map[i][j];
            }
        }

        return dp[rowLen - 1][colLen - 1];
    }


    private int n;
    private int m;
    private int min;
    private int[][] grid;
    private int[][] memo;

    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        n = grid.length;
        m = grid[0].length;
        this.grid = grid;
        min = Integer.MAX_VALUE;
        memo = new int[n][m];
        f(0, 0, 0);
        return min;
    }

    private void f(int i, int j, int currentSum) {
        //System.out.println(i+" " +j+" "+currentSum);
        if (i == n - 1 && j >= m || i >= n && j == m - 1) {
            min = Math.min(min, currentSum);
            return;
        }
        if (i >= n || j >= m) return;
        if (memo[i][j] == 0 || memo[i][j] > currentSum) {
            memo[i][j] = currentSum;
            f(i, j + 1, currentSum + grid[i][j]);
            f(i + 1, j, currentSum + grid[i][j]);
        }

    }


    private int randomNum() {
        int min = 0;
        int max = 10;
        return new Random().nextInt(max - min) + min;
    }

    public static void main(String[] args) {
        Code18 code18 = new Code18();

        int[][] map = {
                {1, 3, 5, 9, 2},
                {2, 1, 3, 4, 3},
                {5, 2, 6, 7, 1},
                {6, 8, 4, 3, 3}
        };

        int n = 9999;
        int[][] map1 = new int[n][n];
        System.out.println("======================================");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map1[i][j] = code18.randomNum();
//                System.out.print(map1[i][j] + ",");
            }
//            System.out.println();
        }
        System.out.println("======================================");


        System.out.println("开始计时");
        long startTime = System.currentTimeMillis();   //获取开始时间
        int res = code18.dpFun(map1);
        long endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println(res);
        System.out.println("程序运行时间： " + (double) ((endTime - startTime) / 1000.0) + "s");


        int [][] memory = new int[n][n];
        startTime = System.currentTimeMillis();
        res = code18.recFunc(map1,memory,n-1,n-1);
        endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println(res);
        System.out.println("程序运行时间： " + (double) ((endTime - startTime) / 1000.0) + "s");

//        startTime=System.currentTimeMillis();
//        res = code18.recFun2(map1,0,0);
//        endTime=System.currentTimeMillis(); //获取结束时间
//        System.out.println(res);
//        System.out.println("程序运行时间： "+(double)((endTime-startTime)/1000.0)+"s");
//
//
//        startTime=System.currentTimeMillis();
//     //   res = code18.recFun(map1,0,0);
//        endTime=System.currentTimeMillis(); //获取结束时间
//        System.out.println(res);
//        System.out.println("程序运行时间： "+(double)((endTime-startTime)/1000.0)+"s");


    }

}
