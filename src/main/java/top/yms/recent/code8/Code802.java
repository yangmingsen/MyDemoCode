package top.yms.recent.code8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Code802 {

    static class RunTaskA implements Runnable {
        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println(name+" Run TaskA");
            try {
                Thread.sleep(360*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name+" Run TaskA End");
        }
    }

    static class RunTaskB implements Runnable {
        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println(name+" Run TaskB");
            int i=0;
            while (i<10000) {
                i++;
                if (i > 1000) {
                    i = i / 100;
                }
            }

            System.out.println(name+" Run TaskB End");
        }
    }


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        System.out.println("Main Execute....");
        executorService.execute(new RunTaskA());
        executorService.execute(new RunTaskB());
    }

}
