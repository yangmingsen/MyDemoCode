package top.yms.past11.concurrent;

import java.util.concurrent.locks.ReentrantLock;

public class TestConcurrent4 {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        try {
            int x = 1;
        } catch (Exception e) {

        } finally {
            reentrantLock.unlock();
        }
    }
}
