package top.yms.past11.test;

import java.util.*;

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }
}
public class Solution {
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        Stack<Integer> mockA = new Stack<>();
        int popAIndex = 0;
        for (int i=0; i<pushA.length; i++) {
            mockA.push(pushA[i]);

            while ( !mockA.empty() && (mockA.peek().intValue() == popA[popAIndex]) ) {
                mockA.pop();
                popAIndex++;
            }
        }
        return mockA.isEmpty();
    }

    public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList <Integer> ans = new ArrayList<>();
        int row1 = 0, row2 = matrix.length;
        int col1 = 0, col2 = matrix[0].length;

        while (true) {
            // ==>
            for (int i = col1; i < col2; i++) ans.add(matrix[row1][i]);
            row1++;
            if (row1>=row2) break;

            //
            for (int i=row1; i<row2; i++) ans.add(matrix[i][col2-1]);
            col2--;
            if (col1>=col2) break;

            for (int i=col2-1; i>=col1; i--) ans.add(matrix[row2-1][i]);
            row2--;
            if (row1>=row2) break;

            for(int i=row2-1; i>=row1; i--) ans.add(matrix[i][col1]);
            col1++;
            if (col1>=col2) break;
        }

        return ans;
    }

    public void Mirror(TreeNode root) {
        if ( root == null || root.left == null || root.right==null ) return;

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        if (root.left != null) Mirror(root.left);
        if (root.right != null) Mirror(root.right);
    }

    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        Queue <TreeNode> queue = new LinkedList();
        if (root == null)  return ans;
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();

            if (treeNode.left != null) {
                queue.add(treeNode.left);
            }
            if (treeNode.right != null) {
                queue.add(treeNode.right);
            }
            ans.add(treeNode.val);
        }
        return ans;
    }

    public int TreeDepth(TreeNode root) {

        if (root == null) {
            return 0;
        } else {
            int lt = TreeDepth(root.left);
            int rt = TreeDepth(root.right);

            return (lt>rt ? lt : rt)+1;
        }

    }

    public static void main(String[] args) {

        Solution so = new Solution();


    }


}