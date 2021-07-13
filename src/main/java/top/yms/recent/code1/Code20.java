package top.yms.recent.code1;

import top.yms.past11.solu.TreeNode;

import java.util.ArrayList;

public class Code20 {
    int v1,v2,minVal,i;
    private void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.left);
            i++;
            v2 = root.val;
            if (i>1) {
                int tmpV = Math.abs(v2 - v1);
                minVal = Math.min(tmpV,minVal);
                v1 = v2;
            } else {
                v1 = v2;
            }
            inOrder(root.right);
        }
    }

    public int minDiffInBST(TreeNode root) {
        i = 0;
        minVal = Integer.MAX_VALUE;
        inOrder(root);
        return minVal;
    }


    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<>();
        //4,2,6,1,3,null,null
//        integers.add(4);
//        integers.add(2);
//        integers.add(6);
//        integers.add(1);
//        integers.add(3);
//        integers.add(null);
//        integers.add(null);

        //[90,69,null,49,89,null,52,null,null,null,null]
        integers.add(90);
        integers.add(69);
        integers.add(null);
        integers.add(49);
        integers.add(89);
        integers.add(null);
        integers.add(52);
        integers.add(null);
        integers.add(null);
        integers.add(null);

        TreeNode treeNode = new TreeNode(integers);

        Code20 code20 = new Code20();
        System.out.println(code20.minDiffInBST(treeNode.root));


    }
}
