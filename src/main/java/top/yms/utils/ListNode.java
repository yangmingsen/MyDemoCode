package top.yms.utils;

import java.util.Arrays;
import java.util.Random;

import static top.yms.past11.solution.Solution.swap;

public class ListNode {
    public int val;
    public ListNode next;


    public ListNode(int val) {
        this.val = val;
        this.next = null;
    }

    public ListNode() {}

    /**
     * 以指定数组arr建立链表
     * @param arr
     * @return
     */
    public static ListNode create(int [] arr) {
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


    private static int ipvot(int [] arr, int l, int r) {
        int pivot = arr[r];
        for(int i=l; i<r; i++) {
            if (pivot > arr[i]) {
                swap(arr, l++, i);
            }
        }
        swap(arr, l, r);

        return l;
    }

    private static void qSort(int [] arr, int l, int r) {
        if (l < r) {
            int p = ipvot(arr, l, r);
            qSort(arr, l, p-1);
            qSort(arr, p+1, r);
        }
    }

    public static void qSort(int [] arr) {
        if (arr == null) return;
        int r = arr.length;
        if (r == 1) return;
        qSort(arr, 0, r-1);
    }

    /**
     * 自动创建n个单向链表数据并排序(true正序/false逆序)
     * @param n
     * @param sort
     * @return
     */
    public static  ListNode create(int n, boolean sort) {
        int[] noSortArr = autoGen(n);
        qSort(noSortArr);
        if(!sort) {
            int temp;
            for(int i=0; i<n/2; i++) {
                temp = noSortArr[i];
                noSortArr[i] = noSortArr[n-i-1];
                noSortArr[n-i-1] = temp;
            }
        }
        System.out.println("排序后数据=>"+Arrays.toString(noSortArr));
        return create(noSortArr);
    }

    /**
     * 自动创建n个单向链表数据
     * @param n
     * @return
     */
    public static  ListNode create(int n) {

        return create(autoGen(n));
    }

    /**
     *  自动创建n个单向链表数据
     * @param n
     * @param min
     * @param max
     * @return
     */
    public static  ListNode create(int n, int min, int max) {

        return create(autoGen(n, min, max));
    }


    public  static int [] autoGen(int n ) {

        return autoGen(n, 0, 100);
    }

    /**
     * 自动生成0-100之间的整形数据
     * @param n
     * @param min 最小
     * @param max 最大
     * @return
     */
    public static int [] autoGen(int n, int min, int max) {
        Random random = new Random();
        int [] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = random.nextInt(max-min)+min;
        }
        System.out.println("生成数据=>"+Arrays.toString(res));

        return res;
    }

    public static int autoGenOne(int min, int max) {
        Random random = new Random();
        return random.nextInt(max-min)+min;
    }


    /**
     * 获取链表中倒数第k个节点
     * @param k
     * @return
     */
    public ListNode getKthFromEnd(int k) {
        return getKthFromEnd(this, k);
    }
    public static ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null) return head;

        ListNode fast = head;
        ListNode slow = head;

        while (fast!= null && k>0) {
            fast = fast.next;
            k--;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }

    public void print() {
        print(this);
    }

    /**
     * 打印
     * @param head
     */
    public static void print(ListNode head) {
        ListNode tmpHead = head;
        if (tmpHead == null) {
            System.out.println("Linked=[]");
        }
        System.out.print("Linked=[");
        while (tmpHead != null) {
            if (tmpHead.next == null) {
                System.out.print(tmpHead.val);
            } else {
                System.out.print(tmpHead.val+",");
            }
            tmpHead = tmpHead.next;
        }
        System.out.println("]");

    }

    /**
     * 合并两个有序(升序)列表
     * @param l1
     * @param l2
     * @return
     */
    public static  ListNode doMerge(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode node;
        if (l1.val < l2.val) {
            node = l1;
            node.next = doMerge(l1.next, l2);
        } else {
            node = l2;
            node.next = doMerge(l1, l2.next);
        }

        return node;

    }

}
