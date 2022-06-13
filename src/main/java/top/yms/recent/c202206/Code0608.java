package top.yms.recent.c202206;

import org.junit.jupiter.api.Test;
import top.yms.utils.ListNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;

public class Code0608 {
    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        if (root == null) return root;

        Queue<Node> que = new LinkedList();
        que.add(root);
        while (!que.isEmpty()) {
            int size = que.size();
            Node lastNode = null;
            for(int i=0; i<size; i++) {
                Node poll = que.poll();
                if (poll.left != null) que.add(poll.left);
                if (poll.right != null) que.add(poll.right);

                if (lastNode !=  null) {
                    lastNode.next = poll;
                }
                lastNode = poll;

                if (i + 1 == size) {
                    poll.next = null;
                }

            }
        }
        return root;
    }



    static class MyCalendar {
        TreeMap<Integer, Integer> calendar;

        MyCalendar() {
            calendar = new TreeMap();
        }

        public boolean book(int start, int end) {
            Integer prev = calendar.floorKey(start),
                    next = calendar.ceilingKey(start);
            if ((prev == null || calendar.get(prev) <= start) &&
                    (next == null || end <= next)) {
                calendar.put(start, end);
                return true;
            }
            return false;
        }
    }

    @Test
    public void test1614() {
        int [] arrs = {21, 72, 40, 18, 49, 12, 2, 14, 23, 23};
        TreeMap<Integer, Integer> map = new TreeMap<>();

        for(int m : arrs) {
            map.put(m,m);
        }


        System.out.println(map.floorKey(15));
        System.out.println(map.ceilingKey(15));

    }

    @Test
    public void gen() {
        int monthDay = 31;
        for(int i=1; i<=monthDay; i++) {
            if (i <10 ) {
                System.out.print("'0"+i+"', ");
            } else {
                System.out.print("'"+i+"', ");
            }
        }
        System.out.println();
        for(int i=1; i<=monthDay; i++) {

                System.out.print(ListNode.autoGenOne(1,50)+", ");

        }

    }

    @Test
    public void dd() {

        String sss ="alipay_record_20220607_191433.xlsx";
        String[] split = sss.split("\\.");
        System.out.println(split[0]);

    }





}
