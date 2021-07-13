package top.yms.past11.test1;

public class Test101 {
    public static void main(String[] args) {

        int x = 1;
        char cc = '你';
        for (int i = 0; i < 300; i++) {
            char c = (char)((int)cc+i);
            char c2 = (char)((int)cc-i);
            System.out.print(c+" "+c2+" ");
            if (x%100 == 0) System.out.println();
        }

    }

    public static void printBinary(int k) {
        StringBuilder str =new StringBuilder();
        if (k == 0) {System.out.println("0");return;}

        int k1 = k;
        while (k>0) {
            int r = k&1;
            str.append(r);
            k = k >> 1;
        }
        System.out.println(k1+"="+str.reverse());
    }


}
