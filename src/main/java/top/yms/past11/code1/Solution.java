package top.yms.past11.code1;


public class Solution {

    public int Sum_Solution(int n) {
        int sum = n;
        boolean t = (n > 0) && (sum += Sum_Solution(n-1))>0;
        return sum;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int [] numbers = {3,32,321};
        System.out.println(solution.Sum_Solution(5));
    }
}
