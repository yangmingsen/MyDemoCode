package top.yms.recent.c202110;

public class SleepUtils {
    public static void second(int i) {
        try {
            Thread.sleep(i* 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
