package top.yms.recent.c202101_07.code6;

import top.yms.utils.TreeNode;

import java.util.*;

public class Code666 {
    //root = null
    //
    public int[] levelOrder1(TreeNode root) {
        if (root == null) return new int[0];
        if (root.left == null && root.right ==null) return new int[]{root.val};

        List<Integer> resList = new ArrayList<>();
        LinkedList<TreeNode> tmpQueue = new LinkedList<>();
        tmpQueue.add(root);
        while (tmpQueue.size() != 0) {
            TreeNode firstNode = tmpQueue.getFirst();
            resList.add(firstNode.val);
            if(firstNode.left != null) tmpQueue.add(firstNode.left);
            if(firstNode.right != null) tmpQueue.add(firstNode.right);
            tmpQueue.removeFirst();
        }
        int len = resList.size();int [] res = new int[len];int i=0;
       for(Integer num : resList) res[i++]=num;

       return res;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> resList = new ArrayList<>();
        if (root == null) return resList;
        if (root.left == null && root.right ==null)  {
            resList.add(new LinkedList<Integer>() {{
                add(root.val);
            }});
            return resList;
        }

        LinkedList<TreeNode> tmpQueue = new LinkedList<>();
        tmpQueue.add(root); boolean reserve = false;
        while (tmpQueue.size() != 0) {
            LinkedList<TreeNode> tmpQueue2 = new LinkedList<>();
            List<Integer> tmpList = new ArrayList<>();
            for(TreeNode node : tmpQueue) {
                tmpList.add(node.val);

                if(node.left != null) tmpQueue2.add(node.left);
                if(node.right != null) tmpQueue2.add(node.right);
            }
            if(!reserve) {
                resList.add(tmpList);
            } else {
                Collections.reverse(tmpList);
                resList.add(tmpList);
            }
            tmpQueue = tmpQueue2;
            reserve = !reserve;
        }
        return resList;
    }


    public static void main(String[] args) {
        Code666 code666 = new Code666();
        ArrayList<Integer> objects = new ArrayList<>();
        objects.add(6);
        objects.add(3);
        objects.add(9);
        objects.add(-1);
        objects.add(4);
        objects.add(8);
        objects.add(10);

        TreeNode treeNode = new TreeNode(objects);


        code666.levelOrder(null);
        List<List<Integer>> lists = code666.levelOrder(treeNode.root);
        for(List<Integer> list : lists) {
            for(Integer num : list) {
                System.out.print(num+", ");
            }
            System.out.println();
        }


    }

}
