package top.yms.recent.c202201;

import java.util.concurrent.ArrayBlockingQueue;

public class Code20220118 {

    

    static ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(10);


    static int cnt = 0;

    public static void main(String[] args) throws InterruptedException {

       Thread x =  new Thread(() -> {

            try {
        //        Thread.sleep(3*1000);

                while (true) {
                    String take = blockingQueue.take();
                    System.out.println("tak => "+take);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cnt++;


        });
       x.start();

       Thread y= new Thread(() -> {
            try {
                for (int i = 0; i <10; i++) {
                    blockingQueue.put("send => "+i);
                    Thread.sleep(2*1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
           cnt++;
        });
       y.start();


       Thread.sleep(100*1000);



    }


}
