package top.yms.past11.app;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class Activity extends Thread {
    private Integer goodsId;
    private ReentrantLock lock;
    private Condition continueRun;
    private int status; // 1 进行 2暂停  3重置
    private int remainingTime; //秒
    private int initTime;

    public Activity(int goodsId, Integer status, int initTime) {
        this.goodsId = goodsId;
        this.status = status;
        this.lock = new ReentrantLock();
        this.continueRun = lock.newCondition();
        this.initTime = initTime * 60;
        this.remainingTime = initTime * 60;
    }

    public String getRemainingTimeString() {
        int ttime = this.remainingTime;
        int minute = ttime / 60;
        int second = ttime % 60;

        StringBuilder stringBuilder = new StringBuilder();
        if (minute < 10) {
            stringBuilder.append("0").append(minute);
        } else {
            stringBuilder.append(minute);
        }
        stringBuilder.append(":");
        if (second < 10) {
            stringBuilder.append("0").append(second);
        } else {
            stringBuilder.append(second);
        }

        return stringBuilder.toString();
    }

    public void setStatus(Integer status) {
        if (status != 2) {
            final ReentrantLock lock = this.lock;
            lock.lock();
            try {
                continueRun.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
        this.status = status;

    }

    public void run() {


        while (remainingTime > -1) {
            if (status == 2) {
                final ReentrantLock lock = this.lock;
                try {
                    lock.lockInterruptibly();
                    continueRun.await();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            } else if (status == 3) {
                remainingTime = initTime;
                status = 1;
                continue;
            }

            try {
                Thread.sleep(1*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("剩余时间: "+getRemainingTimeString());
            remainingTime--;
        }

    }
}

public class App {
    public static void main(String[] args) {
        Activity activity = new Activity(234, 1, 15);
        activity.start();

        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 50; i++) {
            System.out.print("input: ");
            String s = scanner.nextLine();
            int status = Integer.parseInt(s);

            activity.setStatus(status); //控制线程状态
        }

    }
}
