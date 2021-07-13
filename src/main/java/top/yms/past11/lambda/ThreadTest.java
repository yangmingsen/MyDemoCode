package top.yms.past11.lambda;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ThreadTest {

    public static void main(String[] args) {
        Executor executor = Executors.newFixedThreadPool(5);

        new Thread().start();

        executor.execute(() -> {
            System.out.println("Hello world");
            System.out.println("waht this");
        });


        new Thread(() -> System.out.println("this is thread"));



    }

}
