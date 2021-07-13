package top.yms.past11.sword;

import java.util.Arrays;

public class Code1 {
    int f(int n, int m) {
        if (n == 1) return 0;
        int x = f(n - 1, m);
        return (x + m) % n;
    }

    int LastRemaining_Solution(int n, int m) {
        if (n <= 0) return -1;
        return f(n, m);
    }

    public String replacePace(String strs) {
        if (strs.length() == 0) return strs;
        char [] chars = new char[100];
        int len = strs.length();
        int p1 = len-1;
        int p2 = -1;
        for (int i = 0; i < len; i++) {
            chars[i] = strs.charAt(i);
        }
        int ct = 0;
        for (int i = 0; i < len; i++) {
            if (chars[i] == ' ') {
                ct++;
            }
        }
        p2 = len+(ct*2) -1;

        while (p1 != p2) {
            char c = chars[p1];
            if (c == ' ') {
                chars[p2--] = '0';
                chars[p2--] = '2';
                chars[p2--] = '%';
            } else {
                chars[p2] = chars[p1];
                p2--;
            }
            p1--;
        }

        String res = new String(chars);
        return res;
    }

    public static void main(String[] args) {

        StringBuilder st = new StringBuilder();
        st.append("sdf");
        char [] strs = new char[100];
        String str1 = "hello world  what this yms   xiy ";
        String str2 = "";
        String str3 = "hello";
        String str4 = " ";
        String str5 = "  ";
        String str6 = " xy ";
        String str7 = "             xy ";
        strs = Arrays.copyOf(str1.toCharArray(), str1.length());

        Code1 code1 = new Code1();
        System.out.println(code1.replacePace(str7));


    }

}
