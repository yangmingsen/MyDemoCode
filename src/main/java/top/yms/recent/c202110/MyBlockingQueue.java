package top.yms.recent.c202110;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueue<E> {


    /**
     * The queued items
     */
    private final Object[] items;

    /**
     * items index for next take, poll, peek or remove
     */
    private int takeIndex;

    /**
     * items index for next put, offer, or add
     */
    private int putIndex;

    /**
     * Number of elements in the queue
     */
    private int count;

    /*
     * Concurrency control uses the classic two-condition algorithm
     * found in any textbook.
     */

    /**
     * Main lock guarding all access
     */
    private final ReentrantLock lock;

    /**
     * Condition for waiting takes
     */
    private final Condition notEmpty;


    public MyBlockingQueue(int capacity) {
        this.items = new Object[capacity];
        lock = new ReentrantLock(false);
        notEmpty = lock.newCondition();

    }

    private static void checkNotNull(Object v) {
        if (v == null)
            throw new NullPointerException();
    }

    public void put(E e) throws InterruptedException {
        checkNotNull(e);
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (count == items.length)
                notEmpty.await();

            final Object[] items = this.items;
            items[putIndex] = e;
            if (++putIndex == items.length)
                putIndex = 0;
            count++;

            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public E take() throws InterruptedException {
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (count == 0)
                notEmpty.await();

            final Object[] items = this.items;
            @SuppressWarnings("unchecked")
            E x = (E) items[takeIndex];
            items[takeIndex] = null;
            if (++takeIndex == items.length)
                takeIndex = 0;
            count--;

            notEmpty.signal();

            return x;
        } finally {
            lock.unlock();
        }
    }
}
