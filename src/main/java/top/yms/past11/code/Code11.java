package top.yms.past11.code;

import java.util.HashMap;
import java.util.Map;

public class Code11 {


    Map<String, Integer> map = new HashMap<>();
    int fun(int [][] arr, int i, int j) {
        if (i+1==arr.length) {
            return arr[i][j];
        }
        String num = i+""+j;
        if (map.get(num) != null) {
            return map.get(num);
        }
        int res =  Math.max(fun(arr,i+1,j), fun(arr,i+1,j+1))+arr[i][j];
        map.put(i+""+j, res);
        return res;
    }

    int dp(int [][] arr) {
        int rowSize = arr.length;
        int colSize = arr[rowSize-1].length;
        int [] f = new int[colSize];

        for(int i=0; i<colSize; i++) {
            f[i] = arr[rowSize-1][i];
        }

        for(int i = rowSize-2; i>=0; i--) {
            for(int j=0; j<arr[i].length; j++) {
                f[j] = Math.max(arr[i][j]+f[j], arr[i][j]+f[j+1]);
            }
        }

        return f[0];
    }



    public static void main(String[] args) {
        int[][] arr = {
                {32},
                {83, 68},
                {40, 37, 47},
                { 5,  4, 67, 22},
                {79, 69, 78, 29, 63},
                { 0, 71, 51, 82, 91, 64},
                { 0, 71, 51, 82, 91, 64, 94},
                { 20, 71, 51, 82, 91, 64, 88, 203},
                { 20, 71, 51, 82, 91, 64, 88, 203, 934},
                { 0, 71, 51, 82, 91, 64, 88, 203, 934, 2345},
                { 0, 71, 51, 82, 91, 64, 88, 203, 934, 2345, 34345},
                { 0, 71, 51, 82, 91, 64, 88, 203, 934, 2345, 34345, 3234},
                { 0, 71, 51, 82, 91, 64, 88, 203, 934, 2345, 34345, 3234, 23108}
        };

        Code11 code11 = new Code11();
        System.out.println(code11.fun(arr,0,0));
        System.out.println(code11.dp(arr));

    }

}
