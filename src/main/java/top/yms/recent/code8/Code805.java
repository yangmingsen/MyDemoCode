package top.yms.recent.code8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Code805 {



    static class RunTaskC implements Runnable{

        @Override
        public void run() {
            System.out.println("Hell world");
            try {

                Thread.sleep(3*1000);
            } catch (InterruptedException e) {

            }


            System.out.println("Hell world2");

        }
    }

    public static void main(String[] args) {

        System.out.println("Do some thing");

        ThreadUtil.doTask(new RunTaskC());

        ThreadUtil.doTask(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hell world222");
                try {

                    Thread.sleep(3*1000);
                } catch (InterruptedException e) {

                }



                System.out.println("Hell world23333");

            }
        });

        System.out.println("yes i cna od");

        try {
            Thread.sleep(4*1000);
        } catch (InterruptedException e) {

        }
        System.out.println("main Go");

    }

}
