package top.yms.recent.c202101_07.code1;

public class Code3 {

    boolean fun1(int [] arr) {
        if (arr == null) return false;
        int len = arr.length;
        if (len == 0) return false;
        if (len ==1) return true;

        return checkSeuenceOfBST(arr, 0, len-1);
    }

    boolean checkSeuenceOfBST(int [] arr, int i, int j) {
        if (i >= j) return true;
        int p = i;
        while (arr[p] < arr[j]) p++;
        int m = p;
        while (arr[p] > arr[j]) p++;
        return p==j && checkSeuenceOfBST(arr,i,m-1) && checkSeuenceOfBST(arr,m,j-1);
    }

    public static void main(String[] args) {
        Code3 code3 = new Code3();

        //test demo
        int [] arr = {3,7,5,10,20,6,9};
        int [] arr1 = {3,7,5,10,20,15,9};
        int [] arr2 = {};
        int [] arr3 = {1};
        int [] arr4 = null;
        int [] arr5 = {6,7,11,10,9};
        int [] arr6 = {6,7,10,9};
        int [] arr7 = {5,6,7,9};
        int [] arr8 = {5,10,7,9};
        System.out.println("expect=false|actual="+code3.fun1(arr));
        System.out.println("expect=true|actual="+code3.fun1(arr1));
        System.out.println("expect=false|actual="+code3.fun1(arr2));
        System.out.println("expect=true|actual="+code3.fun1(arr3));
        System.out.println("expect=false|actual="+code3.fun1(arr4));
        System.out.println("expect=true|actual="+code3.fun1(arr5));
        System.out.println("expect=true|actual="+code3.fun1(arr6));
        System.out.println("expect=true|actual="+code3.fun1(arr7));
        System.out.println("expect=false|actual="+code3.fun1(arr8));
    }
}
