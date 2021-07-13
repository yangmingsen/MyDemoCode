package top.yms.recent.code1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Code17 {

    static class TaskA implements Runnable{

        Lock lock = new ReentrantLock();

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            List<String> list = TarMemory.MEMORY.get();
            System.out.println(threadName+":第一次查询memory: ");
            System.out.println("getList="+list);
            if (list!=null) {
                System.out.println("listSize="+list.size());
                print(list);
            }

            List<String> list1 = getList(20);
            TarMemory.MEMORY.set(list1);

            int size = list1.size();
            int ps = 10;
            int loopN = size/ps;
            if (size % ps != 0) loopN++;
            for(int i=1; i<=loopN; i++) {
                List<String> list2 = TarMemory.doRange1(list1, i, ps);
                print(list2);
            }

            System.out.println(threadName+":再次查询memory: ");
            List<String> list3 = TarMemory.MEMORY.get();
            System.out.println("getList="+list3);
            if (list3!=null) {
                System.out.println("listSize="+list3.size());
                print(list3);
            }

            try {
                Thread.sleep(60*1000);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

    static class TarMemory {
        static ThreadLocal<List<String>> MEMORY = new ThreadLocal<>();
        public static List<String> doRange(List<String> mappingList, int page, int pageSize) {
            int len = mappingList.size();
            int start = (page-1)*pageSize;
            int doublePageSize = pageSize*2;
            boolean isEnd = (len - start) < doublePageSize;
            int theEnd = start+pageSize;
            int end = !isEnd ? theEnd : len;

            return mappingList.subList(start,end);
        }

        public static List<String> doRange1(List<String> mappingList, int page, int pageSize) {
            int len = mappingList.size();
            int start = (page-1)*pageSize;
            int doublePageSize = pageSize*2;
            boolean isEnd = (len - start) < doublePageSize;
            int theEnd = start+pageSize;
            int end = !isEnd ? theEnd : len;

            List<String> res = mappingList.subList(start, end);
            if (isEnd) {
                MEMORY.set(null); // help GC
            }
            return res;
        }


    }


    private static void print(List<String> list) {
        list.forEach(x -> System.out.print(x+","));
        System.out.println();
    }

    private static List<String> getList(int n) {
        ArrayList<String> strings = new ArrayList<>();
        for (int i=0; i<n; i++) {
            strings.add(i+"");
        }

        return strings;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        TaskA taska = new TaskA();
        TaskA taskb = new TaskA();
        executorService.execute(taska);
        executorService.execute(taskb);
    }
}
