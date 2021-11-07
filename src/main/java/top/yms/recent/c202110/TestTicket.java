package top.yms.recent.c202110;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import top.yms.recent.c202101_07.code4.MyHttpUtils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class TestTicket {

    private static volatile boolean flag = false;

    private static final int threadNum = 3;

    private static ExecutorService executorService = Executors.newFixedThreadPool(threadNum);
    private static ExecutorService executorService1 = Executors.newCachedThreadPool();

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    private static CountDownLatch latch = new CountDownLatch(threadNum);

    private static final String url = "http://localhost:8080/order/ticket2";

    static class RunTask implements Runnable {

        private String name;

        public RunTask(String name) {
            this.name = name;
        }



        @Override
        public void run() {
            int andIncrement = atomicInteger.getAndIncrement();

            System.out.println(name+"开始------");
            while (andIncrement != threadNum) {
                andIncrement = atomicInteger.get();
            }




            int taskNum = 10;
            CountDownLatch latch1 = new CountDownLatch(taskNum);

            for(int i=1; i<=10; i++) {
                TicketRequest ticketRequest = new TicketRequest();
                ticketRequest.setRequestName(name);
                ticketRequest.setSeatId("15A"+ i);
                ticketRequest.setFloor("A15");

                executorService1.execute(() -> {
                    for(int j=0; j<3; j++) {
                        Gson gson = new Gson();
                        String gsonStr = gson.toJson(ticketRequest);
                        System.out.println(name+"|开始请求|"+gsonStr);

                        try {
                            String rsStr = MyHttpUtils.doJsonRequest(url, null, gsonStr);
                            JSONObject jsonObject = JSONObject.parseObject(rsStr);
                            System.out.println(name+"|获得请求结果|"+rsStr);
                            System.out.println();
                            String status = jsonObject.get("status").toString();
                            if (status.equals("3")||rsStr.indexOf("座位已经被预定")>0) {
                                break;
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    latch1.countDown();
                });

            }

            try {//等待子线程执行完毕
                latch1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            latch.countDown();
            System.out.println(name+"结束------");
        }
    }

    static class RequestTask implements Runnable {
       private TicketRequest ticketRequest;
        CountDownLatch latch1;
        private String name;

        public RequestTask(String name, TicketRequest ticketRequest, CountDownLatch latch1) {
            this.ticketRequest = ticketRequest;
            this.latch1 = latch1;
            this.name = name;
        }

        @Override
        public void run() {
            for(int j=0; j<3; j++) {
                Gson gson = new Gson();
                String gsonStr = gson.toJson(ticketRequest);
                System.out.println(name+"|开始请求|"+gsonStr);

                try {
                    String rsStr = MyHttpUtils.doJsonRequest(url, null, gsonStr);
                    JSONObject jsonObject = JSONObject.parseObject(rsStr);
                    System.out.println(name+"|获得请求结果|"+rsStr);
                    System.out.println();
                    String status = jsonObject.get("status").toString();
                    if (status.equals("3")||rsStr.indexOf("座位已经被预定")>0) {
                        break;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            latch1.countDown();
        }
    }


    public static void main(String[] args) {
        long startTime=System.currentTimeMillis();   //获取开始时间

        for(int i=0; i<threadNum; i++) {
            executorService.execute(new RunTask("ATask"+i));
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime=System.currentTimeMillis(); //获取结束时间

        System.out.println("程序运行时间： "+(double)((endTime-startTime)/1000.0)+"s");


    }



}
