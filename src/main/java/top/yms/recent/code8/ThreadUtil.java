package top.yms.recent.code8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadUtil {
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void doTask(Runnable task) {
        executorService.execute(task);
    }

}
