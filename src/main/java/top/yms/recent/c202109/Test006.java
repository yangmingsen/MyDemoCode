package top.yms.recent.c202109;


import top.yms.utils.TreeNode;

import java.util.*;
//输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
//
//
//
// 示例 1:
//
// 给定二叉树 [3,9,20,null,null,15,7]
//
//
//    3
//   / \
//  9  20
//    /  \
//   15   7
//
// 返回 true 。
//
//示例 2:
//
// 给定二叉树 [1,2,2,3,3,null,null,4,4]
//
//
//       1
//      / \
//     2   2
//    / \
//   3   3
//  / \
// 4   4
//
//
// 返回 false 。
//
//
//
// 限制：
//
//
// 0 <= 树的结点个数 <= 10000
//
//
// 注意：本题与主站 110 题相同：https://leetcode-cn.com/problems/balanced-binary-tree/
//
//
// Related Topics 树 深度优先搜索 二叉树 👍 202 👎 0
public class Test006 {


    public boolean isStraight(int[] nums) {
        Set<Integer> repeat = new HashSet<>();
        int max = 0, min = 14;
        for(int num : nums) {
            if(num == 0) continue; // 跳过大小王
            max = Math.max(max, num); // 最大牌
            min = Math.min(min, num); // 最小牌
            if(repeat.contains(num)) return false; // 若有重复，提前返回 false
            repeat.add(num); // 添加此牌至 Set
        }
        return max - min < 5; // 最大牌 - 最小牌 < 5 则可构成顺子
    }

    public boolean isStraight2(int [] nums) {
        if (nums == null) return false;

        boolean [] map = new boolean[14];
        int max=0, min=14;

        for(int num : nums) {
            if (num == 0 ) continue;
            max = Math.max(max, num);
            min = Math.min(min, num);

            if (map[num]) return false;
            map[num] = true;
        }
        return max - min < 5;

    }

    public boolean isStraight3(int [] nums) {
        if (nums == null) return false;
        int numsLen = nums.length;
        if (numsLen < 5) return false;

        Arrays.sort(nums);
        int zeroCnt = 0;
        int lastZeroIdx = -1;
        for(int i=0; i<numsLen; i++) {
            if (nums[i] != 0) {lastZeroIdx = i; break; }
            zeroCnt++;
        }

        for(int i=numsLen-2; i>=lastZeroIdx; i--) {
            int tmpNum = nums[i+1] - nums[i];
            if (tmpNum == 0) return false; //indicate 有相同数
            if (tmpNum != 1) {
                zeroCnt -= (tmpNum-1);
            }
        }

        return zeroCnt >= 0;
    }

    public static void main2(String[] args) {
        Test006 test006 = new Test006();
        int [] test1 = {1,2,3,4,5};
        int [] test2 = {0,0,1,2,5};
        int [] test3 = {0,0,1,8,5};

        System.out.println(test006.isStraight(test1));
        System.out.println(test006.isStraight2(test1));
        System.out.println(test006.isStraight3(test1));
        System.out.println();
        System.out.println(test006.isStraight(test2));
        System.out.println(test006.isStraight2(test2));
        System.out.println(test006.isStraight3(test2));
        System.out.println();
        System.out.println(test006.isStraight(test3));
        System.out.println(test006.isStraight2(test3));
        System.out.println(test006.isStraight3(test3));

    }

//       1
//      / \
//     2   2
//    / \
//   3   3
//  / \
// 4   4

    private int getMaxDeep(TreeNode root) {
        if (root == null) return 0;

        int left = getMaxDeep(root.left)+1;
        int right = getMaxDeep(root.right)+1;

        return Math.max(left , right);
    }

    public boolean doCheckBalance(TreeNode root) {
        int left = getMaxDeep(root.left);
        int right = getMaxDeep(root.right);
        if (Math.abs(left-right) <= 1) return false;

        return doCheckBalance(root.left) && doCheckBalance(root.right);
    }


    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;

        doComp(root, 1);
        System.out.println("max="+max+",min="+min);
        return max-min <=1;
    }


    int max,min;
    public void doComp(TreeNode root, int cnt) {

        if (root == null|| (root.left == null && root.right==null) ) {
            if ((root!=null)) {
                cnt = cnt+1;
            }
            max = Math.max(cnt, max);
            min = Math.min(cnt, min);
            return;
        }
        doComp(root.left, cnt+1);
        doComp(root.right, cnt+1);
    }

    public static void main(String[] args) {
        Test006 test006 = new Test006();
        boolean res = test006.isBalanced(test006.testCase3());
        System.out.println(res);
    }

//       1
//      / \
//     2   2
//    / \
//   3   3
//  / \
// 4   4

    public TreeNode testCase() {
        TreeNode root = new TreeNode(1);
        TreeNode l2 = root.left = new TreeNode(2);
        TreeNode r2 = root.right = new TreeNode(2);

        TreeNode l3 = l2.left = new TreeNode(3);
        TreeNode r3 = l2.right = new TreeNode(3);

        TreeNode l4 = l3.left = new TreeNode(4);
        TreeNode r4 = l3.right = new TreeNode(4);

        return root;
    }

    public TreeNode testCase1() {
        TreeNode root = new TreeNode(1);
        TreeNode r2 = root.right = new TreeNode(2);

        TreeNode l3 = r2.left = new TreeNode(3);


        return root;
    }

//    3
//   / \
//  9  20
//    /  \
//   15   7
    public TreeNode testCase3() {
        TreeNode root = new TreeNode(3);
        TreeNode l2 = root.left = new TreeNode(9);
        TreeNode r2 = root.right = new TreeNode(20);

        TreeNode l3 = l2.left = new TreeNode(15);
        TreeNode r3 = l2.right = new TreeNode(7);

        return root;
    }


    static class Solution {
        public boolean isBalanced(TreeNode root) {
            return height(root) >= 0;
        }

        public int height(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int leftHeight = height(root.left);
            int rightHeight = height(root.right);
            if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
                return -1;
            } else {
                return Math.max(leftHeight, rightHeight) + 1;
            }
        }
    }



}
