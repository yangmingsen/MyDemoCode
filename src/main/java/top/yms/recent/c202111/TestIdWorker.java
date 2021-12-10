package top.yms.recent.c202111;

import org.junit.jupiter.api.Test;
import top.yms.past11.id.IdWorker;

public class TestIdWorker {

    private IdWorker idWorker = new IdWorker();

    @Test
    public void test() {
            System.out.println(idWorker.nextId());

            Long l = Long.MAX_VALUE;
            System.out.println(l);
            System.out.println(l.toString().length());

            System.out.println(idWorker.nextId().length());
    }

}
