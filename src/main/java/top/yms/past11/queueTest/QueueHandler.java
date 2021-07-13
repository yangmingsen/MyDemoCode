package top.yms.past11.queueTest;

import java.util.Map;

public class QueueHandler extends Thread{

    private Map<String, String> map = null;

    public void run() {
        while (true) {
            String take = MessageQueue.take();

            handler(take);
        }
    }

    private void handler(String take) {
        System.out.println();
        System.out.println("QueueHandler收到消息："+take);
        int type = 1;
        if (take.equals("message4")) {
            type=4;
        }

        try {
            switch (type) {
                case 1: {
                    System.out.println("处理消息(type=1): "+take);
                }
                break;

                case 4: {
                    System.out.println("处理消息(type=4): "+take);
                    map.put(take,"23423");
                }
                break;
            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
