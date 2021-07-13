package top.yms.past11.solu;


import java.util.List;
import java.util.Stack;

public class TreeNode {
    public Integer val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int x) { val = x; left=right=null;}

    int size = 0;
    public TreeNode root;

    public TreeNode(List<Integer> list) {
        if (list.size() == 0) return;
        root = new TreeNode(list.get(0));
        int len = list.size();
        for(int i=1; i<len; i++) {
            root = insert(list.get(i), root);
        }
    }

    public TreeNode insert(Integer ele, TreeNode root) {
        if (ele == null) return root;
        if (root == null) {
            size++;
            root = new TreeNode(ele);
            if (root == null) System.out.println("无法申请内存错误");
            else {
                root.left = root.right = null;
            }
        } else if(ele < root.val){
            root.left = insert(ele, root.left);
        } else {
            root.right = insert(ele, root.right);
        }
        return root;
    }

    public void preOrderLoop(TreeNode node) {
        Stack<TreeNode> stack  = new Stack<>();
        stack.push(null);
        while (!stack.isEmpty()) {
            if (node != null) {
                stack.push(node.right);
                showData(node);
                node = node.left;
            } else {
                node = stack.pop();
            }
        }
    }

    public void preOrderLoop2(TreeNode node) {
        Stack<TreeNode> stack  = new Stack<>();
        while (node !=null || !stack.isEmpty()) {
            while (node != null) {
                showData(node);
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                TreeNode pop = stack.pop();
                node = pop.right;
            }
        }
    }

    public void inOrderLoop(TreeNode node) {
        Stack<TreeNode> stack  = new Stack<>();
        while (node !=null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                TreeNode pop = stack.pop();
                showData(pop);
                node = pop.right;
            }
        }
    }


    public void postOrderLoop(TreeNode node) {
        Stack<TreeNode> stack  = new Stack<>();
        TreeNode cur = null;
        TreeNode pre = null;
        stack.push(node);

        while (!stack.isEmpty()) {
            cur = stack.peek();
            if ( (cur.left == null && cur.right==null) || (pre != null &&(pre==cur.left || pre==cur.right) )) {
                showData(cur);
                stack.pop();
                pre = cur;
            } else {
                if (cur.right != null) stack.push(cur.right);
                if (cur.left != null) stack.push(cur.left);
            }
        }

    }


    public int getSize() {return size;}


    private void showData(TreeNode node) {
        System.out.print(node.val+", ");
    }


    public void preOrder(TreeNode node) {
        if (node != null) {
            showData(node);
            preOrder(node.left);
            preOrder(node.right);
        }
    }


    public void inOrder(TreeNode node) {
        if (node != null) {
            inOrder(node.left);
            showData(node);
            inOrder(node.right);
        }
    }


    public void postOrder(TreeNode node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            showData(node);
        }
    }
}
