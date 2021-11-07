package top.yms.recent.c202110;

public class Singleton {
    private static volatile Singleton singleton = null;

    private Singleton(){}

    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                singleton = new Singleton();
            }
        }

        return singleton;
    }
}
