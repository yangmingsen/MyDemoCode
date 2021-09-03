package top.yms.recent.c202109;


import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test004 {

    static ExecutorService executorService = Executors.newFixedThreadPool(4);

    static BarInfo [] barInfos1 = new BarInfo[3];

    private static void printBar() {

        while (true) {
            BarInfo[] barInfos = barInfos1;

            StringBuilder tmpStr = new StringBuilder();
            tmpStr.append("\r");
            for(BarInfo bi : barInfos) {
                double pencent1 = (bi.current * 1.0d) / (bi.total * 1.0d);
                tmpStr.append(bi.name).append(" (").append(pencent1).append(")%%  ").
                        append(bi.current).append(" => ").append(bi.total).append("\n");
            }
            System.out.printf(tmpStr.toString());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    private static class BarInfo {
        public int id;
        public String name;
        public long current;
        public long total;
        public long last;

        public BarInfo(int id, String name, long current, long total) {
            this.id = id;
            this.name = name;
            this.current = current;
            this.total = total;
            this.last = current;
        }

        @Override
        public String toString() {
            return "BarInfo{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", current=" + current +
                    ", total=" + total +
                    ", last=" + last +
                    '}';
        }
    }


    public static void main(String[] args) {

        for(int i=0; i<3; i++) {
            final  int idx = i;
            executorService.execute(() -> {
                int min = 30;
                int max = 50;
                int x = new Random().nextInt(max-min)+min;

                long total = 3456;
                long current = 0;

                barInfos1[idx] = new BarInfo(idx, "任务"+idx, current, total);

                BarInfo bi = barInfos1[idx];
                while (current < total) {
                    int nn = new Random().nextInt(max-min)+min;
                    bi.current+=nn;

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            });
        }

        executorService.execute(() -> {
            printBar();
        });

    }
}
