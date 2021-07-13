package top.yms.past11.code;

public class Code22 {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (String s: strs) {
            int[] count = countzeroesones(s);
            for (int zeroes = m; zeroes >= count[0]; zeroes--)
                for (int ones = n; ones >= count[1]; ones--)
                    dp[zeroes][ones] = Math.max(1 + dp[zeroes - count[0]][ones - count[1]], dp[zeroes][ones]);
        }
        return dp[m][n];
    }
    public int[] countzeroesones(String s) {
        int[] c = new int[2];
        for (int i = 0; i < s.length(); i++) {
            c[s.charAt(i)-'0']++;
        }
        return c;
    }

    public static void main(String[] args) {
        Code22 code22 = new Code22();
        String[] strs1 = {"10", "0001", "111001", "1", "0"};
        String[] strs2 = {"10", "0", "1"};
        String[] strs3 = {"10"};
        String[] strs33 = {"0"};
        String[] strs4 = {"00", "000", "0000"}; // => 0
        String [] strs5 = {"0","0","1","1"};
        String [] strs6 = {"001", "110","0000","0000"};
        //m = 9, n =2; right => 3 ; my => 2
        String [] strs7 = {"111","1000","1000","1000"};
        System.out.println(code22.findMaxForm(strs4,1,1));
    }


}
