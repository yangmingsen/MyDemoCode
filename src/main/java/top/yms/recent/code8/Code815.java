package top.yms.recent.code8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Code815 {

    private static SingleQueue singleQueue = new SingleQueue();

    static class SingleQueue {
        private int n;
        private boolean flag = false;

        public synchronized int get() {
            if (!flag) {
                try {
                    wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            flag = false;
            notify();
            return n;
        }

        public synchronized void put(int n) {
            if (flag) {
                try {
                    wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            this.n = n;
            flag = true;
            notify();
        }

    }

    static class Producer implements Runnable{

        @Override
        public void run() {
            int n = 0;
            while (true) {
                singleQueue.put(n++);
                try {
                    Thread.sleep(1000*1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable {
        @Override
        public void run() {

            while (true) {
                int i = singleQueue.get();
                System.out.println("get: "+i);
            }

        }
    }

    public static void main(String[] args) {
        for(byte i =0; i<129; i++) {
            System.out.println(i);
        }
        ReentrantLock lock = new ReentrantLock();


    }

}
