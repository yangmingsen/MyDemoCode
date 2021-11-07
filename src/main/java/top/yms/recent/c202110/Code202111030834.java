package top.yms.recent.c202110;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;

public class Code202111030834 extends AnyBody {


    void combination(char [] str) {
        if (str == null) return;

        int len = str.length;
        LinkedList<Character> result = new LinkedList<>();
        for(int i=1; i<=len; i++) {
            combinationCore(str,i,result,0);
        }

    }

    void combinationCore(char [] str, int number, LinkedList<Character> result, int i) {
        int len = str.length;
        if (number == 0)  {
            for(Character c : result) {
                System.out.print(c);
            }
            System.out.println();
            return;
        }

        if (i == len) return;
        result.add(str[i]);
        combinationCore(str, number-1, result, i+1);

        result.removeLast();
        combinationCore(str, number, result, i+1);

    }

    @Test
    public void test() {
        int n = 3;
        int [] nums = new int[n];
        doNumber(nums,n,0);
   }

   public void doNumber(int [] nums, int len, int idx) {
        int n = 10;
        if (len == idx) {
            System.out.println(Arrays.toString(nums));
            return;
        }

        for(int i=0; i<10; i++) {
            nums[idx] = i;
            doNumber(nums,len, idx+1);
        }
   }

    public int Sum_Solution(int n) {
        int sum = n;
        boolean t = (n > 0) && (sum += Sum_Solution(n-1))>0;
        return sum;
    }

    public int Sum_Solutio2n(int n) {
        int sum = (int) (Math.pow(n,2) + n);
        //System.out.println(sum);
        return sum>>1;
    }

    @Test
    public void test01() {
        int n = 100;
        System.out.println(Sum_Solution(n));
        System.out.println(Sum_Solutio2n(n));
    }

    @Override
    public void doWhat() {

    }

    @Test
    public void doSingle() {
        Singleton instance = Singleton.getInstance();
        System.out.println(instance);
    }
}
