package top.yms.recent.code7;

public class Code703 {

    private int maxSum(int [] nums) {
        if (nums == null) return 0x80000000;
        int tmpMax =0;
        int ans = 0x7fffffff;
        int len = nums.length;
        for(int i=0; i<len; i++) {
            tmpMax = Math.max(tmpMax+nums[i], nums[i]);
            ans = Math.max(tmpMax,ans);
        }
        return ans;

    }

    public static void main(String[] args) {
        int [] nums = {-2,1,-3,4,-1,2,1,-5,4};
        Code703 code703 = new Code703();
        System.out.println(code703.maxSum(nums));
    }
}
