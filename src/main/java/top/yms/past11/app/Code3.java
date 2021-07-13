package top.yms.past11.app;

public class Code3 {
    class Node {
        int val;
        Node next;
        Node(int val) {
            this.val = val;
        }
    }

    Node create(int n) {
        Node  head = new Node(0);
        Node  next  = head;
        for (int i = 1; i < 9; i++) {
            Node node = new Node(i);
            next.next = node;
            next = node;
        }
        return head;
    }


    class BinaryNode {
        int val;
        BinaryNode left;
        BinaryNode right;
    }







    Node func(Node root ) {
        Node res = null;
        Node node = root;
        Node pre = null;

        while (node != null) {
            Node nextNode = node.next;
            if (nextNode == null) res = node;
            node.next = pre;
            pre = node;
            node = nextNode;
        }

        return res;
    }

    public static void main(String[] args) {
        Code3 code3 = new Code3();
        Node func = code3.create(9);
        Node node = code3.func(func);
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

}
