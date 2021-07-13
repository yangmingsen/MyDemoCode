package top.yms.past11.queueTest;

public class OfferMessageThread  extends Thread{
    public void run() {
        int i = 0;
        while (true) {
            MessageQueue.offer("message"+i);
            try {
                Thread.sleep(1*1000);
            }catch (Exception e) {

            }
        i++;
        }
    }
}
