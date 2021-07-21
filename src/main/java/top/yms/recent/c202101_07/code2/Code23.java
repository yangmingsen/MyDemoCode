package top.yms.recent.c202101_07.code2;

public class Code23 {



    public static int add(int num1,int num2) {
        int sum = 0;//不带进位的结果
        int carry = 0;//进位
        while(num2 != 0){
            sum = num1 ^ num2;
            carry = (num1 & num2) << 1;
            num1 = sum;
            num2 = carry;
        }

        return num1;
    }

    public static void main(String[] args) {
        int a =1;
        int b = 2;
        System.out.println(add(a,b));
    }
}
