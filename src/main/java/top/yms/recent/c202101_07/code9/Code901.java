package top.yms.recent.c202101_07.code9;

import top.yms.utils.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class Code901 {

    public void fun(char [] str) {
        int len = str.length;
        LinkedList<Character> cList = new LinkedList<>();
        for(int i=0; i<len ;i++) {
            doFun(str, i+1, 0, cList);
        }
    }

    private void doFun(char [] c, int number, int idx, LinkedList<Character> cList) {
        if (number == 0) {
            System.out.println(cList);
            return;
        }
        if (idx == c.length) return;

        cList.add(c[idx]);
        doFun(c, number-1, idx+1, cList);

        cList.removeLast();
        doFun(c, number, idx+1, cList);
    }

    public void arrange(char [] str) {
        if (str == null) return;
        int len = str.length;
        if (len == 0|| len==1) return;

        doArrange(str,0,len);
    }

    void swap(char [] str, int i, int j) {
        char tmp = str[i];
        str[i]=str[j];
        str[j]=tmp;
    }

    public void arr(char [] str, int i, int len) {
        if (i == len) {
            System.out.println(Arrays.toString(str));
        }
        for(int s = i; s<len; s++) {
            swap(str,s,i);
            arr(str, i+1, len);
            swap(str,s,i);
        }
    }

    public void doArrange(char [] str, int s, int len) {
        if (s == len) {
            System.out.println(Arrays.toString(str));
        }

        for (int i=s; i<len; i++) {
            swap(str,i,s);
            doArrange(str,s+1,len);
            swap(str,i,s);
        }

    }

    /**
     * 递归处理
     * @param nums 数值数组
     * @param len 数值位数长度
     * @param indx 开始点
     */
    private void doRec(int [] nums, int len, int indx) {
        if ((len) == indx) {
            System.out.println(Arrays.toString(nums));
            return;
        }
        for(int i=0; i<10; i++) {
            nums[indx] = i;
            doRec(nums, len, indx+1);
        }

    }

    /**
     * 数值位数
     * @param n
     */
    public void myRec(int n) {
        int [] nums = new int[n];
        doRec(nums, n, 0);
    }



    //前序遍历 preorder = [3,9,20,15,7]
    //中序遍历 inorder = [9,3,15,20,7]
    //
    //返回如下的二叉树：
    //    3
    //   / \
    //  9  20
    //    /  \
    //   15   7
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        int length = preorder.length;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int inorderIndex = 0;
        for (int i = 1; i < length; i++) {
            int preorderVal = preorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) { //处理左子树节点，根据父节点
                node.left = new TreeNode(preorderVal);
                stack.push(node.left);//用作与inorder对比
            } else { //处理右子树节点，根据父节点
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

    public int maxSubArray(int[] nums) {
        int numSize = nums.length;
        int maxSum = 0;
        int res = Integer.MIN_VALUE;
        for(int i=0; i<numSize; i++) {
            maxSum = Math.max(maxSum+nums[i], nums[i]);
            res = Math.max(maxSum, res);
        }
        return res;
    }


    public static void main(String[] args) {
        Code901 code901 = new Code901();
        int [] nums = {-2,-11,-3,4,-1,-12,1,-5,-4};
        System.out.println(code901.maxSubArray(nums));

    }
}
