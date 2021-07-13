package top.yms.recent.code8;

import java.util.Stack;

public class Code807 {


    static class ExpressEmptyExcetption extends RuntimeException {
        public ExpressEmptyExcetption() {
            super();
        }

        public ExpressEmptyExcetption(String msg) {
            super(msg);
        }
    }


    private static int doCompute(int n, int m, char op) throws ExpressEmptyExcetption {
        switch (op) {
            case '+' : return n+m;
            case '-' : return n-m;
            case '*' : return n*m;
            case '/' : return n/m;
            default: throw new ExpressEmptyExcetption(" Error");
        }
    }

    private static boolean isOp(char c) {
        return c == '+' ||  c == '-' ||  c == '*' ||  c == '/';
    }

    private static int doOperator(String str) throws ExpressEmptyExcetption  {
        if (str == null || str.length() == 0) throw new ExpressEmptyExcetption("Empty Error");

        Stack<Integer> opNumber = new Stack<>();
        Stack<Character> op = new Stack<>();

        char[] chars = str.toCharArray();
        int len = chars.length;
        for(int i=0; i<len; i++) {
            char c = chars[i];
            if (Character.isDigit(c)) {
                opNumber.push((int)c -48);
            } else if (isOp(c)) {
                op.push(c);
                continue;
            } else {
                throw new ExpressEmptyExcetption("Operator Error");
            }

            if (!op.empty()) {
                char oop = op.peek();
                if (oop == '*' ||  oop == '/') {
                    int stLen = opNumber.size();
                    if (stLen >= 2) {
                        int op1 = opNumber.pop();
                        int op2 = opNumber.pop();
                        opNumber.push(doCompute(op2, op1, oop));
                        op.pop();
                    }
                }
            }

        }

        while (!op.empty()) {
            int op1 = opNumber.pop();
            int op2 = opNumber.pop();
            char popOp = op.pop();
            opNumber.push(doCompute(op2, op1, popOp));
        }


        return opNumber.pop();
    }

    public static void main(String[] args) throws Exception {

        String str = "1+1";

        System.out.println(doOperator(str));


    }
}
