package top.yms.recent.c202110;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.*;

public class TestMyBlockingQueue {

    static ExecutorService executorService = Executors.newFixedThreadPool(6);

    static MyBlockingQueue blockingQueue = new MyBlockingQueue(10);

    static Vector<Object> vector = new Vector<>();

    static class MyProduer2 implements Callable<List<String>> {
        public List<String> call() {

            return null;
        }
    }



    static class MyProduer implements Runnable{
        private int start;
        private int end;

        public MyProduer(int start, int end) {
            this.start = start;
            this.end = end;
            System.out.println("s="+start+", e="+end);
        }

        public void run() {
            for(int i=start; i<end; i++) {
                try {
                    blockingQueue.put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            latch.countDown();
        }

    }

    private static CountDownLatch latch = new CountDownLatch(6);

    public static void main(String[] args) throws InterruptedException {

        for(int i=1; i<4; i++) {
            executorService.execute(new MyProduer(i*100, (i+1)*100-1));
        }

        for(int i=1; i<4; i++) {
            executorService.execute(() -> {
                final String tName = Thread.currentThread().getName();

                try {
                    while (true) {
                        Object take = blockingQueue.take();
                        vector.add(take);

                        long count = latch.getCount();
                        if (count <= 3) {
                            break;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();

            });
        }

        latch.await();

        vector.stream().sorted().forEach(x -> System.out.print(x+","));



    }

}
