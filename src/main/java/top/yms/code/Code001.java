package top.yms.code;

import java.util.Arrays;

public class Code001 {

    private boolean doCheck(char [] str, int i1, int i2, int i3) {
        for(; i1<=i2;  i1++,i3--) {
            if (str[i1] != str[i3]) {
                return false;
            }
        }
        return true;
    }

    private boolean check(char [] aStr, int len) {
        if (len%2 ==0) {
            int n1 = (len/2)-1;
            int n2 = len/2;
            if (aStr[n1] != aStr[n2]) {
                return false;
            }
            return doCheck(aStr,0,n1-1, len-1);
        } else {
            int n1 = len/2;
            return doCheck(aStr, 0, n1-1, len-1);
        }
    }
    public boolean isPalindrome(String s) {
        if (s == null ) return false;
        if (s.equals(" ")) return true;
        if (s.length() == 1) return true;
        char[] chars = s.toCharArray();
        char [] newArr = new char[chars.length];
        int ni =0;
        for(int i=0; i<chars.length; i++) {
            if (Character.isLetterOrDigit(chars[i])) {
                newArr[ni++] = Character.toLowerCase(chars[i]);
            }
        }
        if (ni <= 1) return true;
        if (ni == 2 && newArr[0]==newArr[1])return true;
        return check(newArr,ni);
    }

    public static void main(String[] args) {
        Code001 code001 = new Code001();
//        boolean b1 = code001.isPalindrome("A man, a plan, a canal: Panama");
//        System.out.println(b1);

        boolean b2 = code001.isPalindrome(".,");
        System.out.println(b2);
    }
}
