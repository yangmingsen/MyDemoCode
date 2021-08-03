package top.yms.recent.c202108;


import java.util.Arrays;

public class Code001 {

    public int[] reversePrint(ListNode head) {
        if (head == null) return new int[]{};

        int n = 0;
        ListNode tmpListNode = head;
        while (tmpListNode != null) {
            n++;
            tmpListNode = tmpListNode.next;
        }
        int [] res = new int[n];

        while (head != null) {
            res[n-1] = head.val;
            n--;
            head = head.next;
        }

        return res;
    }



    public static void main(String[] args) {
        ListNode listNode = ListNode.create(10);
        Code001 code001 = new Code001();
        int[] ints = code001.reversePrint(listNode);
        System.out.println(Arrays.toString(ints));
        int[] ints1 = code001.reversePrint(null);
        System.out.println(Arrays.toString(ints1));

    }
}
