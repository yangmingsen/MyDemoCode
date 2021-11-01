package top.yms.recent.c202109;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test005 {



    public static void main(String[] args) throws Exception{

        ReentrantLock lock = new ReentrantLock();

        //dostome
        lock.lock();

        lock.unlock();



    }
}
