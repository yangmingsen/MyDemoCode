package top.yms.recent.c202101_07.code2;

import top.yms.past11.solu.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Code30 {

    static void show(TreeNode node) {
        System.out.print(node.val+", ");
    }

    static void preOrderRec(TreeNode root) {
        if (root !=null) {
            show(root);
            preOrderRec(root.left);
            preOrderRec(root.right);
        }
    }

    static void preOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        while (root!=null || !stack.empty()) {
            while (root != null) {
                show(root);
                stack.push(root);
                root = root.left;
            }
            if (!stack.empty()) {
                root = stack.pop().right;
            }
        }
    }

    static void inOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.empty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            if (!stack.empty()) {
                TreeNode pop = stack.pop();
                show(pop);
                root = pop.right;
            }
        }
    }

    static void inOrderRec(TreeNode root) {
        if (root != null){
            inOrderRec(root.left);
            show(root);
            inOrderRec(root.right);
        }
    }

    static void postOrderRec(TreeNode root) {
        if (root != null) {
            postOrderRec(root.left);
            postOrderRec(root.right);
            show(root);
        }
    }

    static void postOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre=null,cur;
        stack.push(root);

        while (!stack.empty()) {
            cur = stack.peek();
            if ((cur != null &&(cur.left == null && cur.right == null)) ||
                    (pre != null &&(cur.left == pre || cur.right == pre))) {
                show(cur);
                stack.pop();
                pre = cur;
            } else {
                if (cur.right != null) stack.push(cur.right);
                if (cur.left != null) stack.push(cur.left);
            }
        }
    }



    static List<Integer> genData() {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(10);
        integers.add(5);
        integers.add(15);
        integers.add(3);
        integers.add(7);
        integers.add(5);
        integers.add(0);
        integers.add(4);
        return integers;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(genData());
        TreeNode root = treeNode.root;
        inOrderRec(root);
        System.out.println();
        inOrder(root);

        Code31 code31 = new Code31();
        TreeNode treeNode1 = code31.balanceBST(root);
        System.out.println();
        root = treeNode1;

        inOrderRec(root);
        System.out.println();
        inOrder(root);

    }
}

