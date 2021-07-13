package top.yms.past11.id;

public class Gen {
    public static void main(String[] args) {
        IdWorker idWorker = new IdWorker();
        for (int i = 0; i < 10; i++) {
            System.out.println(idWorker.nextId());
        }
    }
}
