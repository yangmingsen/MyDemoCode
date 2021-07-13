package top.yms.past11.thread;

public class Demo1 {
    static class A extends Thread {
        public void run() {
            while (true) {
                System.out.println("我是A线程在跑");
                try {
                    Thread.sleep(3*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void one() {
            for (int i = 0; i < 5; i++) {
                System.out.println("我被别的线程调用了");
                try {
                    Thread.sleep(3*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("调用完了");
        }
    }

    public static void main(String [] args) {

        A a = new A();
        a.start();

        for (int i = 0; i < 10; i++) {
            a.one();
            System.out.println("main线程打印");
        }

    }

}
