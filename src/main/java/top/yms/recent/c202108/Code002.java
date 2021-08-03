package top.yms.recent.c202108;

import top.yms.past11.solu.TreeNode;

public class Code002 {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null && B == null) return true;
        if (A != null && B != null) {
            return hasSubTree(A,B);
        }
        return false;
    }

    private boolean hasSubTree(TreeNode tree1, TreeNode tree2) {
        boolean ans = false;

        if (tree1 != null && tree2 != null) {
            if (tree1.val == tree2.val) {
                ans = checkTree1HasTree2(tree1,tree2);
            }

            if(!ans) {
                ans = hasSubTree(tree1.left, tree2);
            }

            if(!ans) {
                ans = hasSubTree(tree1.right, tree2);
            }
        }

        return ans;

    }

    private boolean checkTree1HasTree2(TreeNode tree1, TreeNode tree2) {
        if (tree2 == null) {
            return true;
        }

        if (tree1 == null) {
            return false;
        }

        if (tree1.val != tree2.val) {
            return false;
        }

        return checkTree1HasTree2(tree1.left, tree2.left) && checkTree1HasTree2(tree1.right, tree2.right);
    }
}
