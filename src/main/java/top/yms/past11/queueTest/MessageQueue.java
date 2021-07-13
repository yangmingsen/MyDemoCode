package top.yms.past11.queueTest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MessageQueue {
    private static BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(1000);

    private MessageQueue(){}
    /***
     * 如果试图的操作无法立即执行，返回一个特定的值(常常是 true / false)。
     */
    public static boolean offer(String queueMessage) {
        System.out.println("收到消息： "+queueMessage);
        return blockingQueue.offer(queueMessage);
    }

    /***
     * wait for get data
     * @return
     */
    public static String take() {
        try {
            return blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
