package top.yms.recent.code9;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Code904 {

    static class VolatileFeaturesExample {
         volatile long vl = 0L; // 使用volatile声明64位的long型变量
        private Object object = new Object();
        public   void set(long l) throws InterruptedException {
            System.out.println("set before");
            synchronized (object) {
                Thread.sleep(10*1000);
            }
            System.out.println("set after");
            vl = l; // 单个volatile变量的写
        }
        public   void getAndIncrement () {
            vl++; // 复合（多个）volatile变量的读/写
        }
        public   long get() throws InterruptedException {
            System.out.println("get before");
            synchronized (object) {
                Thread.sleep(10*1000);
            }
            System.out.println("get after");
            return vl; // 单个volatile变量的读
        }
    }

    private  static VolatileFeaturesExample featuresExample = new VolatileFeaturesExample();

    private final static CountDownLatch cdl = new CountDownLatch(4);


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);


        for(int i=0; i<1; i++) {
            executorService.execute(() -> {
                for(int ii=0; ii<1; ii++) {
                    try {
                        featuresExample.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            });
        }

        for(int i=0; i<1; i++) {
            executorService.execute(() -> {
                for(int ii=0; ii<1; ii++) {
                    try {
                        featuresExample.set(ii);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            });
        }

    }

}
