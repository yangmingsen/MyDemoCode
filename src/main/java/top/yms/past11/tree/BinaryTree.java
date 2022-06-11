package top.yms.past11.tree;

import top.yms.utils.TreeNode;

import java.util.ArrayList;

public class BinaryTree {
    public static void main(String[] args) {
        int [] arr = {6,3,8,4,7,2,9,1,0};
        ArrayList<Integer> listNode = new ArrayList<>();
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            listNode.add(arr[i]);
        }
        TreeNode root = new TreeNode(listNode);
        root.preOrderLoop2(root.root);
        System.out.println();
        root.inOrderLoop(root.root);
        System.out.println();
        root.postOrderLoop(root.root);

    }
}
