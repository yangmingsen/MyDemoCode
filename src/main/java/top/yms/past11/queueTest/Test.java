package top.yms.past11.queueTest;

public class Test {
    public static void main(String[] args) {
        OfferMessageThread offerMessageThread = new OfferMessageThread();
        QueueHandler queueHandler = new QueueHandler();
        offerMessageThread.start();
        queueHandler.start();

    }
}
