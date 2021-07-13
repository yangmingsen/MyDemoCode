package top.yms.recent.code6;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Code603 {


    private static String match( Date date) {
        final String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String timeStampStr = simpleDateFormat.format(date);
        return timeStampStr;
    }

    static class RunTask implements Runnable{
        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println("threadName="+threadName+" come in");
            try {
                Thread.sleep(20*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("threadName="+threadName+" closed");
        }
    }

    public static void main(String[] args) {
        long ll = 1480166465631L;
        Date date = new Date(ll);
        String match = match(date);
        System.out.println(match);



    }
}
