package top.yms.recent.c202111;

//给定一棵二叉搜索树，请找出其中第k大的节点。
//
//二叉搜索树的第k大的节点
//
// 示例 1:
//
// 输入: root = [3,1,4,null,2], k = 1
//   3
//  / \
// 1   4
//  \
//   2
//输出: 4
//
// 示例 2:
//
// 输入: root = [5,3,6,2,4,null,null,1], k = 3
//       5
//      / \
//     3   6
//    / \
//   2   4
//  /
// 1
//输出: 4
//
//
//
// 限制：
//
// 1 ≤ k ≤ 二叉搜索树元素个数



//leetcode submit region begin(Prohibit modification and deletion)

import org.junit.jupiter.api.Test;
import top.yms.past11.solu.TreeNode;


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
public class FindBinaryMaxK {

    public int findMaxK(TreeNode node, int k) {
        Stack<TreeNode> stack = new Stack<>();
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.right;
            }
            if (!stack.isEmpty()) {
                TreeNode pop = stack.pop();
                k--;
                if (k == 0) {
                    return pop.val;
                }
                node = pop.left;
            }
        }
        return -1;
    }

    public int kthLargest(TreeNode root, int k) {
        if (root == null) return -1;
        return findMaxK(root,k);
    }



    @Test
    public void test() {
        System.out.println(kthLargest(testCase1(), 1));
        System.out.println(kthLargest(testCase2(), 3)); // => 4
    }

    public TreeNode testCase1() {
        int [] dArr = {3,1,4,2};
        TreeNode treeNode = new TreeNode(dArr);
        return treeNode;
    }
    public TreeNode testCase2() {
        int [] dArr = {5,3,6,2,4,1};
        TreeNode treeNode = new TreeNode(dArr);
        return treeNode;
    }


}
