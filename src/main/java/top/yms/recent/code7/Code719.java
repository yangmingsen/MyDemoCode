package top.yms.recent.code7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Code719 {

    static class CodeA implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"-----"+Thread.currentThread().getContextClassLoader());
        }

    }

    public static void main(String[] args) {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(contextClassLoader);


        ExecutorService executorService = Executors.newCachedThreadPool();

        for(int i=0; i<5; i++) {
            executorService.submit(new CodeA());
        }


    }
}
