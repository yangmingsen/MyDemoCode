package top.yms.recent.c202109;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Code20210924 {

    public static final void print() {

    }

    public static void print(int x) {

    }

    public final void pp() {

    }

    public final void pp(int x) {

    }

    static class Aa {
        public static void print(int x) {
            System.out.println(" hello Aa");
        }
    }


    static class Ab extends Aa {
        public static void print(int x) {
            System.out.println(" hello Ab");

        }

        public void pp() {

        }

    }

    public static void main(String[] args) throws Exception{

        Ab.print(3);
        Ab ab = new Ab();
        Ab.print(3);


        FutureTask<String> future = new FutureTask<>(() -> {
            return "hell";
        });
        new Thread(future).start();

        while (!future.isDone()) {
            try {
                Thread.sleep(1*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(future.get());


    }
}
