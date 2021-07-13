package top.yms.recent.code1;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Code15 {
    void arrange(char [] str) {
        if (str == null) return;
        int len = str.length;
        if (len == 0 || len==1) return;
        LinkedList<Character> list = new LinkedList<>();
        for (int i = 1; i <= len; i++) {
            doArrange(str,0,len,i,list);
        }
    }

    void doArrange(char [] str, int i, int len, int num, LinkedList<Character> list) {
        if (num==0) {
            Iterator<Character> iter = list.iterator();
            while (iter.hasNext()) {
                Character next = iter.next();
                System.out.print(next);
            }
            System.out.println();
            return;
        }

        if (i == len) return;

        list.add(str[i]);
        doArrange(str,i+1,len,num-1,list);

        list.removeLast();
        doArrange(str,i+1,len,num,list);
    }

    public static void main(String[] args) {
        Code15 code15 = new Code15();
        char [] str = {'a','b','c'};
        code15.arrange(str);
    }

}
