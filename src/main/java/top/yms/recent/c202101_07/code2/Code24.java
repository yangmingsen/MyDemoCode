package top.yms.recent.c202101_07.code2;

public class Code24 {

    private static int doRec(int n) {
        int sum = n;
        boolean t = (n != 0) && (sum+=doRec(n-1)) >0;
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(doRec(3));
    }

}
