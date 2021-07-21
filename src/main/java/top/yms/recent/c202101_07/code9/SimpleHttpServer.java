package top.yms.recent.c202101_07.code9;

import java.util.concurrent.Callable;

public class SimpleHttpServer {
    private static int doWhat(int x, int y) {
        return x+y;
    }
    public static void main(String[] args) throws Exception {
        Callable<String> stringCallable = () -> {
            return "hello";
        };

        int x = 10;
        int y = 20;
        Callable<Integer> integerCallable = () -> doWhat(x,y);

        Integer call = integerCallable.call();
        System.out.println(call);


    }
}