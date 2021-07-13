package top.yms.past11.app;

import top.yms.past11.solu.TreeNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {

    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        Map<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
        int length = preorder.length;
        for (int i = 0; i < length; i++) {
            indexMap.put(inorder[i], i);
        }
        TreeNode root = buildTree(preorder, 0, length - 1,
                                    inorder, 0, length - 1, indexMap);
        return root;
    }

    public TreeNode buildTree2(int [] pre, int ps, int pe, int [] in, int is, int ie, Map<Integer,Integer> map) {
        if (ps > pe) return null;
        int rootVal = in[ps];
        TreeNode root = new TreeNode(rootVal);
        if (ps == pe) {
            return root;
        } else {
            int rootIdx = map.get(rootVal);
            int leftNodeIdx =rootIdx - is;
            int rightNodeIdx = ie - rootIdx;
            TreeNode leftTree = buildTree2(pre,ps+1, ps+leftNodeIdx,
                    in, is, rootIdx-1, map);
            TreeNode rightTree = buildTree2(pre, pe-rightNodeIdx+1, pe,
                    in,rootIdx+1,ie, map);

            root.left = leftTree;
            root.right = rightTree;

            return root;
        }
    }

    public TreeNode buildTree(int[] preorder, int preorderStart, int preorderEnd,
                              int[] inorder, int inorderStart, int inorderEnd,
                              Map<Integer, Integer> indexMap) {
        if (preorderStart > preorderEnd) {
            return null;
        }
        int rootVal = preorder[preorderStart];
        TreeNode root = new TreeNode(rootVal);
        if (preorderStart == preorderEnd) {
            return root;
        } else {
            int rootIndex = indexMap.get(rootVal);
            int leftNodes = rootIndex - inorderStart, rightNodes = inorderEnd - rootIndex;
            TreeNode leftSubtree = buildTree(preorder, preorderStart + 1, preorderStart + leftNodes,
                    inorder, inorderStart, rootIndex - 1, indexMap);
            TreeNode rightSubtree = buildTree(preorder, preorderEnd - rightNodes + 1, preorderEnd,
                    inorder, rootIndex + 1, inorderEnd, indexMap);
            root.left = leftSubtree;
            root.right = rightSubtree;
            return root;
        }
    }



    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        int length = preorder.length;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        int inorderIndex = 0;
        for (int i = 1; i < length; i++) {
            int preorderVal = preorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                node.left = new TreeNode(preorderVal);
                stack.push(node.left);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex++;
                }
                node.right = new TreeNode(preorderVal);
                stack.push(node.right);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        int [] pre = {6, 3, 2, 1, 0, 4, 8, 7, 9};
        int [] in =  {0, 1, 2, 3, 4, 6, 7, 8, 9};

        Solution solution = new Solution();
        solution.buildTree2(pre,in);

    }
}

