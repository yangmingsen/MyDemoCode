package top.yms.code;

import top.yms.past11.solu.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Code002 {

    private List<Integer> res = null;

    private void doPreOrder(TreeNode root) {
        if (root != null) {
            res.add(root.val);
            doPreOrder(root.left);
            doPreOrder(root.right);
        }
    }

    private List<Integer>  doPreOrder2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack =new Stack<>();
        while (root != null || !stack.empty()) {
            while (root != null) {
                res.add(root.val);
                stack.push(root);
                root = root.left;
            }
            if (!stack.empty()) {
                root = stack.pop().right;
            }
        }
        return res;
    }


    public List<Integer> preorderTraversal(TreeNode root) {
        return doPreOrder2(root);
    }

    public static void main(String[] args) {
        Code002 code66 = new Code002();
        ArrayList<Integer> objects = new ArrayList<>();
        objects.add(6);
        objects.add(3);
        objects.add(9);
        objects.add(-1);
        objects.add(4);
        objects.add(8);
        objects.add(10);

        TreeNode treeNode = new TreeNode(objects);

        System.out.println(code66.preorderTraversal(treeNode.root));


        // code66.inOrder2(root);




    }
}
