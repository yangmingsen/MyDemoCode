package top.yms.recent.c202107;

public class Code003 {
    public int lengthOfLastWord(String s) {
        int sLen = s.length();
        if (sLen == 1 && s.equals(" ")) return 0;
        if (sLen == 1 ) return sLen;

        String[] s1 = s.split(" ");
        if (s1.length == 0) return 0;

        return s1[s1.length-1].length();
    }

    public static void main(String[] args) {
        Code003 code003 = new Code003();
        int i = code003.lengthOfLastWord("      ");
        System.out.println(i);
    }
}
