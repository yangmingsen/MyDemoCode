package top.yms.recent.c202101_07.code1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Code6 {
    static CyclicBarrier cb1 = new CyclicBarrier(2);
    static CountDownLatch cb = new CountDownLatch(2);
    //CountDownLatch 执行countDown()方法后并不会像CyclicBarrier一样等待所有条件(如线程)达到屏障后再执行。
    //CountDownLatch强调的是await()方法的等待是等待所有任务的countDown()方法执行次数达到设定值后再往下执行
    //而CyclicBarrier强调的是所有任务await的次数达到CyclicBarrier设定的屏障值时，所有的任务再往下做
    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 3; i++) {
                            Thread.sleep(1000);
                            System.out.println("_______");
                    }
                    cb.countDown();
                    System.out.println("ACountDown了");
                } catch (Exception e) {
                }
                for (int i = 0; i < 3; i++) {
                    try {
                        Thread.sleep(100);
                        System.out.println("AA1111");
                    }catch (Exception e) {
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 3; i++) {
                        Thread.sleep(3000);
                        System.out.println("+++++++++++");
                    }
                    cb.countDown();
                    System.out.println("CC CountDown了");
                } catch (Exception e) {
                }
                for (int i = 0; i < 3; i++) {
                    try {
                        Thread.sleep(300);
                        System.out.println("CCCC1111");
                    }catch (Exception e) {
                    }
                }
            }
        }).start();

        cb.await();

        System.out.println("所有都完了");

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(600);
                System.out.println("BBBBB333");
            }catch (Exception e) {
            }
        }
        System.out.println("CLOSE"+2);
    }
}
