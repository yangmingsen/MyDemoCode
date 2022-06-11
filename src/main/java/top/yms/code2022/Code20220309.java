package top.yms.code2022;



import org.junit.jupiter.api.Test;
import top.yms.utils.TreeNode;
import top.yms.utils.IntLinkedList;

import java.util.LinkedList;

public class Code20220309 {
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) return root;

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        if (root.left != null) {
            mirrorTree(root.left);
        }

        if (root.right != null) {
            mirrorTree(root.right);
        }

        return root;
    }

    public int lastRemaining3(int n, int m) {
        int res = 0;
        for(int i=2; i<=n; i++) {
            res = (res+m)%i;
        }

        return res;
    }

    // f(n,m) = (f(n-1,m)+m)%n
    public int lastRemaining2(int n, int m) {
        if (n==1) return 0;
        return (lastRemaining2(n-1,m)+m)%n;
    }

    public int lastRemaining1(int n, int m) {
        if (n == 1) return 1;
        IntLinkedList intList = new IntLinkedList();
        for (int i = 0; i < n; i++) {
            intList.add(i);
        }
        int i = 0;
        while (n > 1) {
            i = (i + 2) % n;
            intList.remove(i);
            n--;
        }

        return intList.get(i);
    }

    public int lastRemaining(int n, int m) {
        return lastRemaining1(n, m);
    }

    private static int doJosephus(int n, int m) {
        if (n <= 1) return 0;
        return (doJosephus(n - 1, m) + m) % n;
    }

    @Test
    public void test01() {
        int[] arrs = {8, 5, 10, 3, 6, 9, 11};
        TreeNode treeNode = new TreeNode(arrs);
        treeNode.printForLevelTree();

        mirrorTree(treeNode);
        treeNode.printForLevelTree();

    }

    @Test
    public void test02() {
        int n = 5000000;
        int m = 50000;
       // System.out.println(lastRemaining(n, m));
      //  System.out.println(lastRemaining2(n, m));
        System.out.println(lastRemaining3(n, m));
    }


}
