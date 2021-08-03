package top.yms.recent.c202107;

public class Code007 {
    public static void main(String[] args) {
        int xx=0;

        int x =0;

        xx:
        for(int i=0; i<10; i++) {
            System.out.println("i="+i);
            for(int j=0; j<10; j++) {
                System.out.println("j="+j);
                if (j == 2) {
                    break xx;
                }
            }
        }
        System.out.println("ss ");

    }
}
