package top.yms.recent.c202111;

import org.junit.jupiter.api.Test;

import java.util.Stack;

public class StackPushPopCheck {

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int N = pushed.length;
        if (N == 1) return true;
        Stack<Integer> stack = new Stack<>();

        int j=0;
        for (int x : pushed) {
            stack.push(x);

            while (!stack.isEmpty() && j<N && stack.peek()==popped[j]) {
                stack.pop();
                j++;
            }
        }

        return j==N;
    }


    @Test
    public void tst01() {
        int [] t1 = {1,2,3,4,5};
        int [] ts1 = {5,4,3,2,1};

        System.out.println(validateStackSequences(t1,ts1));

        int [] ts2 = {2,4,5,3,1};
        System.out.println(validateStackSequences(t1,ts2));

        int [] ts3 = {3,5,4,1,2};
        System.out.println(validateStackSequences(t1,ts3));


    }

}
