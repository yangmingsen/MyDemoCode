package top.yms.recent.c202101_07.code7;

import top.yms.past11.solu.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Code722 {

    private void print(TreeNode root) {
        System.out.print(root.val+", ");
    }

    public void preOder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.empty()) {
            if (root != null) {
                print(root);
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                root = root.right;
            }
        }
    }


    public void InOder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.empty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                print(root);
                root = root.right;
            }
        }
    }




    private static List<Integer> create() {
        return new ArrayList<Integer>() {{
            add(10);
            add(5);
            add(9);
            add(2);
            add(6);
            add(20);
        }};
    }

    public static void main(String[] args) throws Exception {
        String str = "你";
        System.out.println(str.getBytes("gb2312").length);

    }

}
