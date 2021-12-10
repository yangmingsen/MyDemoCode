package top.yms.recent.c202111;

public class CodeTest01 {
    public static void main1(String[] args) {
        int a = 1008;
        int b = 507;
        int c = (~b)+1;
        System.out.println(c);
        System.out.println(a-b);
        System.out.println(a+c);
    }

    public static void main(String[] args) {
        byte a = -83;
        byte b = -80;
        byte c = (byte) (a+b);
        System.out.println(c);
    }
}
