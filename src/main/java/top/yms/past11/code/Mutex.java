package top.yms.past11.code;

import java.util.concurrent.atomic.AtomicInteger;


public class Mutex {
    static class Number {
        private AtomicInteger num = new AtomicInteger(0);


        public void autoIncrement() {
            this.num.getAndIncrement();
        }

        public Integer get() {
            return this.num.get();
        }
    }

    static class UpdateNumberThread extends Thread {
        private Number number;
        public UpdateNumberThread(Number number) {
            this.number = number;
        }
        @Override
        public void run() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 2; i++) {
                number.autoIncrement();
            }
        }
    }

    public static void main(String[] args) {
        Number number = new Number();
        UpdateNumberThread updateNumberThread = new UpdateNumberThread(number);
        UpdateNumberThread updateNumberThread2 = new UpdateNumberThread(number);
        UpdateNumberThread updateNumberThread3 = new UpdateNumberThread(number);
        UpdateNumberThread updateNumberThread4 = new UpdateNumberThread(number);


        updateNumberThread.start();
        updateNumberThread2.start();
        updateNumberThread3.start();
        updateNumberThread4.start();


        try {
            Thread.sleep(1*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(number.get());
    }
}