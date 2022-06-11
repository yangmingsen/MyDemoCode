package top.yms.past11.id;

import top.yms.utils.IdWorker;

public class Gen {
    public static void main(String[] args) {
        IdWorker idWorker = new IdWorker();
        for (int i = 0; i < 10; i++) {
            System.out.println(idWorker.nextId());
        }
    }
}
