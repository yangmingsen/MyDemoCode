package top.yms.recent.c202108;

public class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
        this.next = null;
    }

    public ListNode() {}

    public static  ListNode create(int n) {
        ListNode head = new ListNode(0);
        ListNode nextNode = head;
        for(int i=1; i<n; i++) {
            ListNode tmpNode = new ListNode(i);
            nextNode.next = tmpNode;
            nextNode = tmpNode;
        }
        return head;
    }

    public static void print(ListNode head) {
        ListNode tmpHead = head;
        if (tmpHead == null) {
            System.out.println("[]");
        }
        System.out.print("[");
        while (tmpHead != null) {
            System.out.print(tmpHead.val+",");
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
