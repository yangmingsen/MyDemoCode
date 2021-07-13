package top.yms.past11.code;

public class Code17 {
    public boolean isSubsequence(String s, String t) {
        int sSize = s.length();
        int tSize = t.length();
        if (sSize==0 && tSize==0) return true;
        if (sSize == 0) return true;
        if (tSize == 0) return false;


        int maxLen = dpFun(s.toCharArray(),t.toCharArray());
        return maxLen == sSize;
    }

    public int dpFun(char [] s, char [] t) {
        int sLen = s.length;
        int tLen = t.length;
        int [][] dp = new int[sLen][tLen];
        int x = 0;
        for (int i = 0; i < tLen; i++) {
            if (s[0] == t[i]) {
                x = 1;
            }
            dp[0][i] = x;
        }

        x = 0;
        for(int i=0; i<sLen; i++) {
            if (t[0] == s[i]) {
                x = 1;
            }
            dp[i][0] = x;
        }

        for (int i = 1; i < sLen; i++) {
            for (int j = 1; j < tLen; j++) {
                if (s[i] == t[j]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return dp[sLen-1][tLen-1];
    }

    public static void main(String[] args) {
        Code17 code17 = new Code17();
        String s = "abc";
        String t = "ahbgdc";
        System.out.println(code17.isSubsequence(s,t));
    }

}
