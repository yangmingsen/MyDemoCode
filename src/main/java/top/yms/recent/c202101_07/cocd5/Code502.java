package top.yms.recent.c202101_07.cocd5;

import top.yms.past11.solu.TreeNode;

import java.util.ArrayList;

public class Code502 {

    public boolean checkIsPost(int [] arr) {
        if (arr == null) return false;
        int len = arr.length;
        if (len < 2) return true;

        return isPost(arr, 0,len-1);
    }

    public boolean verifyPostorder(int[] postorder) {
        if (postorder == null) return false;
        int len = postorder.length;
        if (len < 2) return true;

        return isPost(postorder,0,len-1);
       // return isPost(postorder,len-1);
    }

    public boolean isPost(int [] arr, int left, int right) {
       if (left > right) return true;
        int rootNum = arr[right];
        int i = left;

        while (arr[i] < rootNum) i++;
        int mid = i;
        while (arr[i] > rootNum) i++;

        return right==i&&isPost(arr,left,mid-1) && isPost(arr, mid, right-1);
    }



    public static void main(String[] args) {
        Code502 code502 = new Code502();
        int [] arr = {8, 3, 5, 12, 18, 15, 10};
        int [] ar2r = {1,2,5,10,6,9,4,3};
        int [] arr3 = {5, 4, 3, 2, 1};
        int [] arr4 = {5, 2, -17, -11, 25, 76, 62, 98, 92, 61};
        System.out.println(code502.verifyPostorder(arr));
    }


    public TreeNode simulate() {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(10);
        integers.add(5);
        integers.add(15);
        integers.add(3);
        integers.add(8);
        integers.add(12);
        integers.add(18);
        return new TreeNode(integers);
    }

}
