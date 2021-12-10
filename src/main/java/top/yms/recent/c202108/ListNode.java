package top.yms.recent.c202108;

import java.util.Arrays;
import java.util.Random;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
        this.next = null;
    }

    public ListNode() {}


    public static ListNode create(int[]arr) {
        if (arr == null || arr.length==0) return null;
        int len = arr.length;

        ListNode head = new ListNode(arr[0]);
        if (len==1) return head;

        ListNode nextNode = head;
        for(int i=1; i<len; i++) {
            ListNode tmpNode = new ListNode(arr[i]);
            nextNode.next = tmpNode;
            nextNode = tmpNode;
        }
        return head;
    }


    public static  ListNode create(int n, boolean sort) {
        int[] noSortArr = autoGen(n);
        Arrays.sort(noSortArr);
        if(!sort) {
            int temp;
            for(int i=0; i<n/2; i++) {
                temp = noSortArr[i];
                noSortArr[i] = noSortArr[n-i-1];
                noSortArr[n-i-1] = temp;
            }
        }
        return create(noSortArr);
    }

    private static int [] autoGen(int n) {
        int min = 0;
        int max = 100;
        Random random = new Random();
        int [] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = random.nextInt(max-min)+min;
        }

        return res;
    }

    public static  ListNode create(int n) {

        return create(autoGen(n));
    }

    public void print() {
        print(this);
    }

    public static void print(ListNode head) {
        ListNode tmpHead = head;
        if (tmpHead == null) {
            System.out.println("[]");
        }
        System.out.print("[");
        while (tmpHead != null) {
            if (tmpHead.next == null) {
                System.out.print(tmpHead.val);
            } else {
                System.out.print(tmpHead.val+",");
            }
            tmpHead = tmpHead.next;
        }
        System.out.print("]");

    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode();
        ListNode listNode1 = listNode.create(10);
        listNode.print(listNode1);
    }
}
