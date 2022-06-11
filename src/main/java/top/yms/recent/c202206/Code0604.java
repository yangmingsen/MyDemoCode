package top.yms.recent.c202206;

import org.junit.jupiter.api.Test;

import java.util.Stack;

public class Code0604 {

    @Test
    public void test1827() {
        int [] pushed1 = {1,2,3,4,5};
        int [] poped1 = {4,5,3,2,1};

        //System.out.println(validateStackSequences(pushed1, poped1));


        int [] pushed2 = {1,2,3,4,5};
        int [] poped2 = {4,3,5,1,2};
        //System.out.println(validateStackSequences(pushed2, poped2));

        int [] pushed3 = {1,0};
        int [] poped3 = {1,0};
        //System.out.println(validateStackSequences(pushed3, poped3));



        int [] pushed4 = {4,0,1,2,3};
        int [] poped4 = {4,2,3,0,1};
        System.out.println(validateStackSequences(pushed4, poped4));

    }

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int len = pushed.length;
        if (len == 1) return true;

        Stack<Integer> stack = new Stack<>();

        int pi=0;
        for(int x : pushed) {
            stack.push(x);
            while (!stack.isEmpty() && stack.peek() == popped[pi]) {
                pi++;
                stack.pop();
            }
        }


        return stack.isEmpty();
    }
}
