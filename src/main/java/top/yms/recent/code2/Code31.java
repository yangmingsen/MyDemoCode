package top.yms.recent.code2;

import top.yms.past11.solu.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Code31 {


    int [] anums = null;
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null) return null;
        int length = nums.length;
        if (length == 0) return null;

        anums = nums;
        return buildBalanceTree1(0,length - 1);
    }

    private TreeNode buildBalanceTree1(int l, int r) {
        int mid = (l+r)>>1;
        TreeNode node = new TreeNode(anums[mid]);
        if (l <= mid -1) {
            node.left = buildBalanceTree1(l,mid-1);
        }
        if (mid+1 <= r) {
            node.right = buildBalanceTree1(mid+1, r);
        }

        return node;
    }


    List<Integer> ascNums = null;
    private void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.left);
            ascNums.add(root.val);
            inOrder(root.right);
        }
    }

    private TreeNode buildBalanceTree(int l, int r) {
        int mid = (r+l)>>1;
        TreeNode node = new TreeNode(ascNums.get(mid));
        if (l <= mid -1) {
            node.left = buildBalanceTree(l,mid-1);
        }
        if (mid+1 <= r) {
            node.right = buildBalanceTree(mid+1, r);
        }

        return node;
    }

    private static final int hello = 624;

    public static Code31 getInstance() {
        return new Code31();
    }




    // FIXME: 2021/1/8
    ///////////////////////////////////////////////////////////////////////////
    // 
    ///////////////////////////////////////////////////////////////////////////
    /* no-op */
    ///////////////////////////////////////////////////////////////////////////
    // what
    
    ///////////////////////////////////////////////////////////////////////////
    public TreeNode balanceBST(TreeNode root) {
        if (root == null) return root;

        ascNums = new ArrayList<>();
        inOrder(root);
        return buildBalanceTree(0,ascNums.size() -1);
    }

    public static void main(String[] args) {
        int [] arr = new int[0];
        System.out.println(arr.length);
    }
}
