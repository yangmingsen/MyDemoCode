package top.yms.recent.c202101_07.code6;

import java.util.concurrent.*;

public class Code605 {
    static long startTime = 0l;
    static class ExeTask implements Runnable {
        @Override
        public void run() {
            long endTime = System.currentTimeMillis();
            System.out.println("程序执行时间间隔： "+(double)((endTime-startTime)/1000.0)+"s");
            try {
                Thread.sleep(3*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    static class MyResult {
        boolean success;
        Object data;

        public MyResult(boolean success, Object data) {
            this.success = success;
            this.data = data;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "MyResult{" +
                    "success=" + success +
                    ", data=" + data +
                    '}';
        }
    }

    static class CallableTask implements Callable<MyResult> {
        @Override
        public MyResult call() throws Exception {
            long endTime = System.currentTimeMillis();
            System.out.println("程序执行时间间隔： "+(double)((endTime-startTime)/1000.0)+"s");
            try {
                Thread.sleep(3*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("I Return");
            return new MyResult(true,"compelete success");
        }
    }


    public static void main(String[] args) {
        startTime = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future<MyResult> futureTask1 = executorService.submit(new CallableTask());

        System.out.println("hello world");

        try {
            for(;;) {
                boolean done = futureTask1.isDone();
                if (done) {
                    MyResult exeTask = futureTask1.get();
                    System.out.println(exeTask);
                    break;
                } else {
                    System.out.println("No Done Continue");
                }
                Thread.sleep(1*1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

    public static void main1(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        ExeTask exeTask = new ExeTask();
        startTime = System.currentTimeMillis();

        //程序执行时间间隔： 1.003s
        //程序执行时间间隔： 4.005s
        //程序执行时间间隔： 7.006s
        //程序执行时间间隔： 10.006s
        //程序执行时间间隔： 13.007s
        scheduledExecutorService.scheduleAtFixedRate(exeTask,1,2, TimeUnit.SECONDS);


        //程序执行时间间隔： 1.002s
        //程序执行时间间隔： 6.006s
        //程序执行时间间隔： 11.008s
        //程序执行时间间隔： 16.009s
        scheduledExecutorService.scheduleWithFixedDelay(exeTask,1,2, TimeUnit.SECONDS);

        //总结：scheduleWithFixedDelay和scheduleAtFixedRate之间时间间隔受到任务执行时间的影响。
        // 看任务的执行时间是否大于delay时间，如果小于两个一样；如果大于那么他们之间的区别如下：
        // 1.scheduleWithFixedDelay它的下一周期任务执行时间为：任务执行时间+delay时间。如上任务执行时间3秒，delay时间2秒。便出现了1,6,11,16...时间间隔5秒。
        //2.而scheduleAtFixedRate它的下一周期任务执行时间为：任务执行时间。如上任务执行时间3秒，delay时间2秒。便出现了1,4,7,10...时间间隔3秒。

    }

}
