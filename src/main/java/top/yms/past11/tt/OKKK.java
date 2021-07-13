package top.yms.past11.tt;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class OKKK {
    public static void main(String[] args) {
        BigDecimal b1 = new BigDecimal("1.099");
        BigDecimal b2 = new BigDecimal("1.099");
        BigDecimal b3 = new BigDecimal("2.099");
        BigDecimal b4 = new BigDecimal("1.099");
        BigDecimal b5 = new BigDecimal("3.099");

        HashMap<BigDecimal, String> map = new HashMap<>();
        map.put(b1,"1");
        map.put(b2,"1");
        map.put(b3,"1");
        map.put(b4,"1");
        map.put(b5,"1");

        Executor executor = Executors.newFixedThreadPool(5);

        AtomicInteger i1 = new AtomicInteger();
        AtomicInteger i2 = new AtomicInteger();
        AtomicInteger i3 = new AtomicInteger();


        executor.execute(() -> {
            for (int i=0; i<100000000; i++) {
            BigDecimal b6 = new BigDecimal("2.099");
            map.put(b6, "1");
                i1.getAndIncrement();
        }

        });

        executor.execute(() -> {
            for (int i=0; i<100000000; i++) {
                BigDecimal b6 = new BigDecimal("1.099");
                map.put(b6, "1");
                i2.getAndIncrement();
            }
        }

        );

        executor.execute(() -> {
            for (int i=0; i<100000000; i++) {
                BigDecimal b6 = new BigDecimal("3.099");
                map.put(b6, "1");
                i3.getAndIncrement();
            }
        }

        );


        while (true) {
            if (i1.get() == 100000000 && i2.get() == 100000000 && i3.get() == 100000000 ) {
                System.out.println(i1.get()+" "+i2.get()+" "+i3.get());
                for(Map.Entry<BigDecimal, String> entry : map.entrySet()) {
                    System.out.println("key="+entry.getKey()+"  value="+entry.getValue());
                }

                break;
            }
        }




    }
}
