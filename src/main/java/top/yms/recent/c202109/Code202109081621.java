package top.yms.recent.c202109;

public class Code202109081621 {

    private boolean doVerifyPostorder(int [] postorder, int s, int e) {
        if (s > e) return true;
        int i = s;

        while (postorder[i] < postorder[e]) i++;
        int mid = i;
        while (postorder[i]>postorder[e]) i++;

        //第一种 不有问题方案
        return i == e ;

        //第二种可以方案
        // return i == e && doVerifyPostorder(postorder, s, mid-1) && doVerifyPostorder(postorder, mid, e-1);


    }

    public boolean verifyPostorder(int[] postorder) {
        if (postorder == null) return false;
        int len = postorder.length;
        if (len == 1) return true;
        if (len == 0) return false;

        return doVerifyPostorder(postorder, 0, len-1);

    }

    public static void main(String[] args) {
        int [] testCase1 = new int[] {3,8,5,12,18,15,10};
        int [] testCase2 = new int[] {8,3,5,12,18,15,10};

        Code202109081621 code202109081621 = new Code202109081621();
        System.out.println(code202109081621.verifyPostorder(testCase1));
        System.out.println(code202109081621.verifyPostorder(testCase2));

    }
}
