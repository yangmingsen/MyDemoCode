package top.yms.recent.code7;

public class Code702 {

    public boolean verifyPostorder(int[] postorder) {
        if (postorder == null) return false;

        int len = postorder.length;
        if (len == 1) return true;

        return doVerifyPostorder(postorder, 0, len-1);
    }

    public boolean doVerifyPostorder(int[] postorder, int s, int e) {
        // 假设 上次 s=0,e=1;  本次所有条件成立i=1,mid=1,e=1 最后调用 1==1&&doVer(p,0,0)&&doVer(p,1,0)
        if (s > e) return true;
        int root = postorder[e];
        int i = s;
        while (postorder[i] < root) i++;
        int mid = i;
        while (postorder[i] > root) i++;

        return i==e&&doVerifyPostorder(postorder, s, mid-1) && doVerifyPostorder(postorder, mid, e-1);
    }

    public static void main(String[] args) {
        //test Data
        int [] arr = {1,4,3,8,10,9,6};
        int [] arr1 = {1,3,10,9,6};
        int [] arr2 = {2,6,3,1,10,9,6};
        int [] arr3 = {1,4,3,10,8,9,6};
        Code702 code702 = new Code702();
        boolean b = code702.verifyPostorder(arr);
        boolean b1 = code702.verifyPostorder(arr1);
        boolean b2 = code702.verifyPostorder(arr2);
        boolean b3 = code702.verifyPostorder(arr3);
        System.out.println(b);
        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);
    }

}
