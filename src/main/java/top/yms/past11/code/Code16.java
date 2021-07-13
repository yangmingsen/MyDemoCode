package top.yms.past11.code;


public class Code16 {

    public int lcs(String str1, String str2) {
        int len1 = str1.length() - 1;
        int len2 = str2.length() - 1;
        recursion(str1.toCharArray(), str2.toCharArray());
        return recLcs(str1.toCharArray(), len1, str2.toCharArray(), len2 );
    }

    private int recLcs(char [] str1, int len1, char [] str2, int len2) {
        if (len1 < 0 | len2 < 0) {
            return 0;
        }

        int x = recLcs(str1, len1-1, str2, len2);
        int y = recLcs(str1, len1, str2, len2-1);

        if(str1[len1] == str2[len2]){
            return Math.max(x,y)+1;
        } else {
            return Math.max(x,y);
        }

    }

    public static void recursion (char[] s1, char[] s2) {
        int maxLen = recursion0 (s1, s1.length-1, s2, s2.length-1);
        System.out.println("maxLen="+maxLen);
    }

    private static int recursion0 (char[] s1, int idx1, char[] s2, int idx2){

        if(idx1 < 0 || idx2 < 0){
            return 0;
        }

        int max1, max2;

        max1 = recursion0 (s1, idx1, s2, idx2 - 1);
        max2 = recursion0 (s1, idx1 - 1, s2,  idx2);

        if (s1[idx1] == s2[idx2]){
            return Math.max(max1, max2) + 1;
        }else{
            return Math.max(max1, max2);
        }
    }


    public int dpFun(char [] str1, char [] str2) {
        int len1 = str1.length;
        int len2 = str2.length;
        int [][] dp = new int[len2][len1];

        int x = 0;
        for (int i = 0; i < len1; i++) {
            if (str1[i]==str2[0]) {
                x = 1;
            }
            dp[0][i] = x;
        }

        x=0;
        for (int i = 0; i < len2; i++) {
            if (str2[i] == str1[0]) {
                x = 1;
            }
            dp[i][0] = x;
        }

        for (int i = 1; i < len2; i++) {
            for (int j = 1; j < len1; j++) {
                if (str2[i] == str1[j]) {
                    dp[i][j] = dp[i-1][j-1]+1;
                } else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }

        return dp[len2-1][len1-1];
    }

    public static void main(String[] args) {
        String str1 = "bsokacde";
        String str2 = "iosbl3c9sd";
        Code16 code16 = new Code16();
        System.out.println(code16.lcs(str1,str2));
        System.out.println(code16.dpFun(str1.toCharArray(),str2.toCharArray()));


    }

}
