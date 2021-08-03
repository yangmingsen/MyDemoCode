package top.yms.recent.c202107;

public class Code005 {

    //input =>
    //[
    //  [1,   4,  7, 11, 15],
    //  [2,   5,  8, 12, 19],
    //  [3,   6,  9, 16, 22],
    //  [10, 13, 14, 17, 24],
    //  [18, 21, 23, 26, 30]
    //]
    // So,我选择左下角作为切入点。 做法类似二分查找一样，比target大往上找，比target小往右找
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int row = matrix.length-1;
        if (row == -1)  return false;
        if (row == 0) {
            if (matrix[0].length == 0) return false;
        }

        int rowLen = matrix[0].length;
        int col = 0;
        while (row>=0 && col < rowLen) {
            if (matrix[row][col] < target) {
                col++;
            } else if(matrix[row][col] > target) {
                row--;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int [][] testCase1 = new int[][] {
                {1,   4,  7, 11, 15},
                {2,   5,  8, 12, 19},
                {3,   6,  9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };

        int [][] testCase2 = new int[][] {
                {}
        };

        int [][] testCase3 = new int[][] {
                {15},
                {19},
                {22},
                {24},
                {30}
        };

        int [][] testCase4 = new int[][] {
                {1,   4,  7, 11, 15}
        };

        int [][] testCase5 = new int[][] {

        };

        Code005 code005 = new Code005();
//        System.out.println(code005.findNumberIn2DArray(testCase1, 5));
//        System.out.println(code005.findNumberIn2DArray(testCase2, 5));
//        System.out.println(code005.findNumberIn2DArray(testCase3, 5));
//        System.out.println(code005.findNumberIn2DArray(testCase4, 5));
        System.out.println(code005.findNumberIn2DArray(testCase5, 0));
    }
}
