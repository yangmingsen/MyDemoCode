package top.yms.recent.c202108;

import java.math.BigInteger;
import java.util.List;

public class Code006 {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    private int charNToIntN(char c) {
        return c-48;
    }

    private ListNode createListNode(String result) {
        char[] chars = result.toCharArray();
        int len = chars.length;
        if (len == 1) {
            return new ListNode(charNToIntN(chars[0]), null);
        } else {
            ListNode head = new ListNode(charNToIntN(chars[len-1]), null);
            ListNode nextNode = head;
            for(int i=len-2; i >=0; i--) {
                ListNode tmpNode = new ListNode(charNToIntN(chars[i]),null);
                nextNode.next = tmpNode;
                nextNode = tmpNode;
            }
            return head;
        }
    }

    private String parseString(ListNode listNode) {
        StringBuilder tmpStr = new StringBuilder();
        while (listNode != null) {
            tmpStr.append(listNode.val);
            listNode = listNode.next;
        }

        return tmpStr.reverse().toString();
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        String s1 = parseString(l1);
        String s2 = parseString(l2);

        BigInteger n1 = new BigInteger(s1);
        BigInteger n2 = new BigInteger(s2);
        BigInteger n3 = n1.add(n2);

        return createListNode(n3.toString());

    }


    public static void main(String[] args) {
        Code006 code006 = new Code006();

        String t1 = "9999999";//"0";//"342";
        String t2 = "9999";//"0";//"465";
        ListNode l1 = code006.createListNode(t1);
        ListNode l2 = code006.createListNode(t2);

        ListNode listNode = code006.addTwoNumbers(l1, l2);

        while (listNode != null) {
            System.out.print(listNode.val+", ");
            listNode= listNode.next;
        }
        System.out.println();

    }


    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;
            if (head == null) {
                head = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return head;
    }



}
