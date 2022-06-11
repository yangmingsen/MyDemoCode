package top.yms.recent.c202112;
//链表中倒数第k个节点
//输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
//
// 例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。
//
//
//
// 示例：
//
//
//给定一个链表: 1->2->3->4->5, 和 k = 2.
//
//返回链表 4->5.
// Related Topics 链表 双指针 👍 305 👎 0


//leetcode submit region begin(Prohibit modification and deletion)


import org.junit.jupiter.api.Test;
import top.yms.utils.ListNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class LinkedKNumber {
    public ListNode getKthFromEnd(ListNode head, int k) {
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

    public ListNode getLastNode(ListNode head, int k) {
        ListNode slow = head;
        ListNode fast = head;

        while (k > 0) {
            fast = fast.next;
            k--;
        }

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;


    }

    @Test
    public void doTest() {
        ListNode listNode = ListNode.create(10);

        ListNode.print(getKthFromEnd(listNode,2));
        ListNode.print(getLastNode(listNode,2));
    }

}
