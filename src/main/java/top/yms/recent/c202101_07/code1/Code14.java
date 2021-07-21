package top.yms.recent.c202101_07.code1;

import java.util.Arrays;

public class Code14 {

    int cnt = 0;

    public void arrange(char [] str) {
        if (str == null) return;
        int len = str.length;
        if (len == 0|| len==1) return;
        doArrange(str,0,len);
    }

    void swap(char [] str, int i, int j) {
        char tmp = str[i];
        str[i]=str[j];
        str[j]=tmp;
    }

    public void doArrange(char [] str, int s, int len) {
        if (s == len) {
            cnt++;
            System.out.println(Arrays.toString(str));
        }

        for (int i=s; i<len; i++) {
            swap(str,i,s);
            doArrange(str,s+1,len);
            swap(str,i,s);
        }

    }


    public static void main(String[] args) {
        char [] str = {'a','b','c'};
        Code14 code14 = new Code14();
        code14.arrange(str);
        System.out.println("cnt="+code14.cnt);
    }
}
