package top.yms.recent.code6;

public class Code668 {

    public static void main2(String[] args) {
        label1:
        for (int i = 0; i < 10; i++) {
            label2:
            if (i > 0) {
                if (i > 8) {
                    break label1;
                }
                System.out.print(i + " ");
                if (i > 3) {
                    break label2;
                }
                System.out.print(" if end ");

            }

            label3: {
                if (i > 5) {
                    break label3;
                }
                System.out.print(", label3 block");
            }
            System.out.println(", for end ");
        }
        System.out.println("hello end the program");
    }

    public static void main(String[] args) {

        canBreak:
        for(int i=0;;i++) {
            i++;
            System.out.println("i="+i);
            for(int j=0; ;j++) {
                System.out.println("j="+j);
                if (i==6) break canBreak;
              if (j== 15) continue canBreak;
            }
//            if (i == 10) continue canBreak;

        }


        System.out.println("Hello yes");
        Thread hello_world = new Thread(() -> System.out.println("hello world"));
        hello_world.start();

        System.out.println("hello word closed");
    }
}
