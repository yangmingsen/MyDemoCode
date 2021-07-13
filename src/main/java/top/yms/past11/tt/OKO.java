package top.yms.past11.tt;


import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class BlockingStack {

    private Stack<String> stack;

    /** Condition for waiting takes */
    private Condition notEmpty;

    /** Condition for waiting puts */
    private Condition notFull;

    private ReentrantLock lock;

    /** Number of elements in the queue */
    private int count;


    BlockingStack() {
        stack = new Stack<>();
        lock = new ReentrantLock();
        notEmpty = lock.newCondition();
        notFull =  lock.newCondition();
        count = 0;
    }

    public boolean offer(String e) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            if (count == 3)
                return false;
            else {
                enqueue(e);
                return true;
            }
        } finally {
            lock.unlock();
        }
    }

    private void enqueue(String x) {
        this.stack.push(x);
        this.count++;
        notEmpty.signal();
    }

    private String dequeue() {
        String pop = this.stack.pop();
        this.count--;
        notFull.signal();
        return pop;
    }

    public String take() throws InterruptedException {
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (count == 0)
                notEmpty.await();
            return dequeue();
        } finally {
            lock.unlock();
        }

    }


}



public class OKO extends Thread{
    private static BlockingStack blockingStack = new BlockingStack();
    public static void main(String[] args) {
        OKO oko = new OKO();
        oko.start();

        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 30; i++) {
            try {
                System.out.println(i+" takeString: "+(i*10));
                blockingStack.offer(""+(i*10));

                Thread.sleep(1*1000);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                String take = blockingStack.take();
                System.out.println("take: "+take);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("close----------");
    }

}
