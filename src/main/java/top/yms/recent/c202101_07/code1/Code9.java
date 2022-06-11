package top.yms.recent.c202101_07.code1;

import top.yms.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Code9 {

    public static void inOrderLoop(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        while (node != null || !stack.empty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            if (!stack.empty()) {
                TreeNode pop = stack.pop();
                showData(pop);
                node = pop.right;
            }

        }
    }



    public static void preOrderLoop(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        while (node != null || !stack.empty()) {
            while (node != null) {
                showData(node);
                stack.push(node.right);
                node = node.left;
            }

            if (!stack.empty()) {
                node = stack.pop();
            }

        }
    }

    private static void showData(TreeNode node) {
        System.out.print(node.val+", ");
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



    public static void main(String[] args) {

        TreeNode treeNode = new TreeNode(create());
        TreeNode root = treeNode.root;
        inOrderLoop(root);
        System.out.println();
        preOrderLoop(root);
        System.out.println();

        treeNode.printForLevelTree();

    }
}
