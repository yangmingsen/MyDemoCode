package top.yms.recent.c202205;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Code20220524 {

    static Object obj = new Object();

    static int max = 10;
    static int cnt = 0;
    static int [] arr = new int[20];

    public static int take() throws InterruptedException {
        synchronized (obj) {
            if (cnt == 0) {
                obj.wait();
            }
            int res = arr[--cnt];
            obj.notify();
            return res;
        }
    }

    public static void put(int v) throws InterruptedException {
        synchronized (obj) {
            if (cnt == max) {
                obj.wait();
            }
            arr[cnt++]=v;
            obj.notify();
        }
    }


    @Test
    public void test1714() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        final CountDownLatch cdl = new CountDownLatch(2);
        executorService.submit(() -> {

           int [] map = new int[100];
            try {
               while (true) {
                   int take = take();
                   System.out.println(Arrays.toString(arr));
                   System.out.println("get => "+take);
                   
                   map[take]++;
                   if (cnt == 0 ) break;

                   Thread.sleep(200);
               }
                System.out.println(Arrays.toString(map));
               cdl.countDown();
           } catch (Exception e) {
               e.printStackTrace();
           }
        });

        executorService.submit( () -> {
           try {
               for(int i=0; i<100; i+=2) {
                   put(i);
                  // Thread.sleep(100);
               }
               put(99);
               cdl.countDown();
           } catch (Exception e) {
               e.printStackTrace();
           }
        });

        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }




}
