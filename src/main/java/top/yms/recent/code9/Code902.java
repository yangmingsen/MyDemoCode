package top.yms.recent.code9;

import top.yms.past11.solu.TreeNode;

import java.util.ArrayList;
import java.util.Stack;

public class Code902 {

    public void inOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.empty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            TreeNode pop = stack.pop();
            System.out.println(pop.val);
            root = pop.right;
        }
    }

    public TreeNode inOrderToDoubleLinked(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();

        TreeNode head = null;
        TreeNode nextNode = null;
        boolean flag = true;

        while (root != null || !stack.empty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            TreeNode pop = stack.pop();
            if (flag) {
                head = pop;
                nextNode = pop;
                flag = false;
            } else {
                nextNode.right = pop;
                pop.left = nextNode;
                nextNode = pop;
            }
            root = pop.right;
            nextNode.right = null;

        }
        return head;
    }



    public static void main(String[] args) {
        int [] arr = {6,3,8,4,7,2,9,1,0};
        ArrayList<Integer> listNode = new ArrayList<>();
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            listNode.add(arr[i]);
        }
        TreeNode root = new TreeNode(listNode);

        Code902 code902 = new Code902();
       // code902.inOrder(root.root);

        TreeNode treeNode = code902.inOrderToDoubleLinked(root.root);

        while (treeNode != null) {
            System.out.println(treeNode.val);
            treeNode = treeNode.right;
        }


    }


}
