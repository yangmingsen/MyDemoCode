package top.yms.recent.c202101_07;

import top.yms.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Code667 {

    static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    static void show(TreeNode node) {
        System.out.print(node.val+", ");
    }

    static void show(Node node) {
        System.out.print(node.val+", ");
    }

    static void show2(Node node) {
        System.out.print(node.val+", ");
    }

    public void inOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        while(root!= null || !stack.empty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            TreeNode topNode = stack.pop();
            show(topNode);
            root = topNode.right;
        }
    }

    public void postOrder(Node root) {
        Stack<Node> stack = new Stack<>();
        Node cur,pre=null;
        stack.push(root);
        while (!stack.empty()) {
            cur = stack.peek();
            if ((cur.left==null && cur.right==null) || (pre!=null && (pre == cur.left || pre == cur.right))) {
                show(cur);
                stack.pop();
                pre = cur;
            } else {
                if (cur.right != null)stack.push(cur.right);
                if (cur.left != null)stack.push(cur.left);
            }
        }
    }

    public void postOrder2(Node node) {
        if (node != null) {
            postOrder2(node.left);
            postOrder2(node.right);
            show(node);
        }
    }

    public void inOrder2(Node root) {
        Stack<Node> stack = new Stack<>();
        while(root!= null || !stack.empty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            Node topNode = stack.pop();
            show2(topNode);
            root = topNode.right;
        }
    }

    public Node treeToDoublyList(Node root) {
        if (root == null) return null;

        Stack<Node> stack = new Stack<>();
        Node first =null; boolean isFirst = true;
        Node nextNode = null;
        while (root != null || !stack.empty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            Node node = stack.pop();
            if(isFirst) {
                nextNode = node;
                first = nextNode;
                isFirst = false;
            } else {
                nextNode.right = node;
                node.left = nextNode;
                nextNode = node;
            }
            root = node.right;
        }

        first.left = nextNode;
        nextNode.right =first;
        return first;
    }

    public static void main(String[] args) {
        Code667 code66 = new Code667();
        ArrayList<Integer> objects = new ArrayList<>();
        objects.add(6);
        objects.add(3);
        objects.add(9);
        objects.add(-1);
        objects.add(4);
        objects.add(8);
        objects.add(10);

        Node root = code66.createNode(objects);
       // code66.inOrder2(root);




    }

    public Node createNode(List<Integer> list) {
        Node root = null;
        for(Integer num : list) {
            root = insert(num,root);
        }
        return root;
    }

    public Node insert(Integer ele, Node root) {
        if (ele == null) return root;
        if (root == null) {
            root = new Node(ele);
            if (root == null) System.out.println("无法申请内存错误");
            else {
                root.left = root.right = null;
            }
        } else if(ele < root.val){
            root.left = insert(ele, root.left);
        } else {
            root.right = insert(ele, root.right);
        }
        return root;
    }




}
