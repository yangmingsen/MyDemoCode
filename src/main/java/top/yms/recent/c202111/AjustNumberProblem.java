package top.yms.recent.c202111;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

//输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数在数组的前半部分，所有偶数在数组的后半部分。
//
//
//
// 示例：
//
//
//输入：nums = [1,2,3,4]
//输出：[1,3,2,4]
//注：[3,1,2,4] 也是正确的答案之一。
//
//
//
// 提示：
//
//
// 0 <= nums.length <= 50000
// 0 <= nums[i] <= 10000
//
public class AjustNumberProblem {

    @Test
    public void tet2() throws ParseException {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE,-20);
        Date date1 = new Date(calendar.getTimeInMillis());
        System.out.println(new SimpleDateFormat("HH-mm-ss").format(date1));
    }

    @Test
    public void tet() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = sdf.parse("20211231");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,1);
        calendar.add(Calendar.MINUTE,-10);
        Date date1 = new Date(calendar.getTimeInMillis());
        System.out.println(sdf.format(date1));


    }


    private Date parseyyyyMMddDate(String dateString) throws ParseException {
        String dateStr =dateString.substring(0, 4)+"-"+dateString.substring(4, 6)+"-"+dateString.substring(6);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return sdf.parse(dateStr);
    }


    private static Date addMonths(Date date, int nums) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH,nums);
        Date date1 = new Date(calendar.getTimeInMillis());

        return date1;
    }

    private static String addMonthsToString(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }




    @Test
    public void doTtt() throws ParseException {
       // Date date = new Date("20211206");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       // Date date = sdf.parse("20211206");
        Date date = parseyyyyMMddDate("20211206");
        System.out.println();

        System.out.println(sdf.format(date));
    }

    void reOrderArray(int [] array) {
        int i = 0;
        for (int j=0; j<array.length; ++j) {
            if ((array[j]&1) == 1) {
                int tmp = array[j];
                for (int k=j-1; k>=i; --k) {
                    array[k+1] = array[k];
                }
                array[i++] = tmp;
            }
        }
    }

    private int[] theOneSolution(int [] nums) {
        int aLen = nums.length;
        int sp = 0;
        int gp = 0;
        while (gp < aLen) {
            while (nums[sp]%2!=0) {
                sp++;
            }
            while (gp<aLen&&nums[gp]%2!=1) {
                gp++;
            }
            if (sp < gp) {
                int t = nums[sp];
                nums[sp] = nums[gp];
                nums[gp] = t;
                sp++;
            }
            gp++;
        }

        return nums;
    }

    @Test
    public void doTest1() {


        int [] arr = new int[]{};
        threeSolution(arr);
        System.out.println(Arrays.toString(arr));

        arr = null;
        threeSolution(arr);
        System.out.println(Arrays.toString(arr));

        arr = new int [] {1};
        threeSolution(arr);
        System.out.println(Arrays.toString(arr));

        arr = new int [] {1,3,5,7,9};
        threeSolution(arr);
        System.out.println(Arrays.toString(arr));

        arr = new int [] {2,4,6,8};
        threeSolution(arr);
        System.out.println(Arrays.toString(arr));

        arr = new int[]{1,2,3,4,5,6,7,8,9};
        threeSolution(arr);
        System.out.println(Arrays.toString(arr));


    }


    /**
     * 相对位置不变 版本
     * @param nums
     * @return
     */
    private int[] threeSolution(int [] nums) {
        if (null == nums || 1>=nums.length) return nums;
        int i=0;
        int nLen = nums.length;
        for(int j=0; j<nLen; j++) {
            if (nums[j]%2==1) {
                int t = nums[j];
                for(int k=j; k>i; k--) {
                    nums[k] = nums[k-1];
                }
                nums[i++] = t;
            }
        }

        return nums;
    }

    private int [] theTwoSulotion(int [] nums) {
        int l = 0;
        int len = nums.length;
        int r = len -1;

        while (l < r) {
            while (l < len &&nums[l]%2==1) {
                l++;
            }
            while (r > l && nums[r]%2==0) {
                r--;
            }

            if (l < r) {
                int t = nums[l];
                nums[l] = nums[r];
                nums[r] = t;
                l++;r--;
            }
        }
        return nums;

    }

    public int[] exchange(int[] nums) {
        if (null == nums) return nums;
        if (nums.length <= 1) return nums;
        return theTwoSulotion(nums);
    }




}
