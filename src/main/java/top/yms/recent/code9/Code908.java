package top.yms.recent.code9;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Code908 {

    Map<Integer, Integer> map = new HashMap<>();
    public int[] twoSum(int[] nums, int target) {
        int len = nums.length;
        int [] result = new int[2];
        for(int i=0; i<len; i++) {
            int tmp = target - nums[i];
            Integer theIdx = map.get(tmp);
            if (theIdx != null) {
                if (theIdx.intValue()!=i) {
                    result[0] = theIdx;
                    result[1] = i;
                    break;
                }
            } else {
                map.put(nums[i], i);
            }
        }
        map.clear();

        return result;
    }

    public static void main(String[] args) {
        Code908 code908 = new Code908();
        int [] nums = {2,7,11,15};
        int target = 9;

        System.out.println(Arrays.toString(code908.twoSum(nums,target)));

    }
}
