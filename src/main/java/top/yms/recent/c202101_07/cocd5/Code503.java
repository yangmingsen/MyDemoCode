package top.yms.recent.c202101_07.cocd5;

import top.yms.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Code503    {


    /**
     * 二叉搜索树转双向链表
     * @param node
     * @return
     */
    public static TreeNode binaryToLinked(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        boolean isFirst = true;
        TreeNode head = null;
        TreeNode nextNode = null;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            if (!stack.empty()) {
                TreeNode pop = stack.pop();
                node = pop.right;
                if (isFirst) {
                    head = pop;
                    nextNode = head;
                    nextNode.right = node;
                    isFirst = false;
                } else {
                    nextNode.right = pop;
                    pop.left = nextNode;
                    nextNode = pop;
                }
            }
        }

        return head;
    }

    public static void inOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            if (!stack.empty()) {
                TreeNode pop = stack.pop();
                System.out.println(pop.val+", ");
                root = pop.right;
            }

        }

    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(create());
        TreeNode root = treeNode.root;
        //inOrder(root);
        TreeNode treeNode1 = binaryToLinked(root);
        TreeNode tail = null;
        while (treeNode1 != null) {
            System.out.print(treeNode1.val+", ");

            if (treeNode1.right == null) {
                tail = treeNode1;
            }
            treeNode1 = treeNode1.right;
        }
        System.out.println();
        while (tail != null) {
            System.out.print(tail.val+", ");
            tail = tail.left;
        }

    }

    private static List<Integer> create() {
        return new ArrayList<Integer>() {{
            add(10);
            add(5);
            add(15);
            add(3);
            add(2);
            add(1);
            add(-1);
            add(-2);
            add(-3);
            add(9);
            add(12);
            add(20);
        }};
    }


}
