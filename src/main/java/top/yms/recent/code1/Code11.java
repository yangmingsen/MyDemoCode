package top.yms.recent.code1;

import java.util.LinkedList;
import java.util.List;

public class Code11 {

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

    public static void main(String[] args) {
         char [] str = {'a','b','c'};
        Code11 code11 = new Code11();
        code11.combination(str);
    }


}
