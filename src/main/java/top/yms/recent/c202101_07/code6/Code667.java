package top.yms.recent.c202101_07.code6;

import top.yms.utils.TreeNode;

import java.util.ArrayList;

public class Code667 {

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left== null && root.right == null) return 1;

        return maxDepth1(root)+1;
    }

    public int maxDepth1(TreeNode root) {
        int x =0,y=0;
        if (root.left != null) x = maxDepth1(root.left)+1;
        if (root.right != null) y = maxDepth1(root.right)+1;

        return Math.max(x,y);
    }

    public static void main(String[] args) {
        ArrayList<Integer> objects = new ArrayList<>();
       objects.add(6);
//        objects.add(3);
//        objects.add(9);
//        objects.add(-1);
//        objects.add(4);
//        objects.add(8);
//        objects.add(10);
//        objects.add(20);


        TreeNode treeNode = new TreeNode(objects);

        Code667 code667 = new Code667();
        System.out.println(code667.maxDepth(treeNode.root));
    }
}
