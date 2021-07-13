package top.yms.past11.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class TestConcurrent2 {

    private static volatile  int num  = 0;

    private static volatile int count = 0;

    private static final AtomicInteger atomicInteger = new AtomicInteger(0);

    private static final ReentrantLock lockR = new ReentrantLock();

    public static void autoIncrement() {
        final ReentrantLock lock = lockR;
        lock.lock();
        try {
            num++;
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void autoSetCount() {
        num = num+1;
    }


    public static void main(String[] args) {


        Executor executor = Executors.newCachedThreadPool();

        for(int i=0; i<10; i++) {
            executor.execute(() -> {
                int n = 0;
                while (true) {
                    if (n == 10000) {
                        break;
                    }
                    n++;
                    //atomicInteger.getAndIncrement();
                    //autoIncrement();
                    autoSetCount();
                }
                System.out.println(Thread.currentThread().getId()+" countOK");
                count++;
            });
        }

        while (true) {
            if (count == 10) {
                System.out.println("num="+num);
                //System.out.println("num="+atomicInteger.get());
                break;
            }
        }


    }


}
