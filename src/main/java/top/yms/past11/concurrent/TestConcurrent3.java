package top.yms.past11.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class TestConcurrent3 {
    static class VolatileFeaturesExample {
        volatile long vl = 0L; // 使用volatile声明64位的long型变量
        public void set(long l) {
            vl = l; // 单个volatile变量的写
        }
        public  void getAndIncrement () {
            vl++; // 复合（多个）volatile变量的读/写
        }
        public long get() {
            return vl; // 单个volatile变量的读
        }
    }

    public static void main(String[] args) {
        Executor executor = Executors.newCachedThreadPool();

        AtomicInteger atomicInteger = new AtomicInteger(0);
        VolatileFeaturesExample vf = new VolatileFeaturesExample();

        for(int i=0; i<10; i++) {
            executor.execute(() -> {
                for(int j = 0; j< 10000; j++) {
                    vf.getAndIncrement();
                }
                atomicInteger.getAndIncrement();
                System.out.println("Thread: "+Thread.currentThread().getName());
            });
        }

        while (true) {
            if (atomicInteger.get() == 10) {
                System.out.println("num="+vf.get());
                break;
            }
        }

    }

}
