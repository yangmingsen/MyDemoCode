package top.yms.recent.c202108;

import top.yms.past11.solu.TreeNode;

public class Code005 {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null && B == null) return true;
        if (A != null && B != null) {
            return hasSubTree(A,B);
        }
        return false;
    }

    private boolean hasSubTree(TreeNode tree1, TreeNode tree2) {

        return false;
    }

    public static void main(String[] args) {
        String helo = "hello";
        StringBuilder tmpStr = new StringBuilder(helo);
        System.out.println(tmpStr.reverse().toString());
    }
}
