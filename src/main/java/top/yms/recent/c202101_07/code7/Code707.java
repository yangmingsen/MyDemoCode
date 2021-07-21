package top.yms.recent.c202101_07.code7;

import java.util.Arrays;
import java.util.LinkedList;

public class Code707 {

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
            System.out.println(result.toString());
            return;
        }

        if (i == len) return;
        result.add(str[i]);
        combinationCore(str, number-1, result, i+1);

        result.removeLast();
        combinationCore(str, number, result, i+1);

    }

    private static int cnt = 0;
    public static void main(String[] args) {
        //abc
        //null
        //empty
        Code707 code707 = new Code707();
        char [] str = {'a','b','c','d','e'};
        //code707.combination(str);
        //code707.combineGroup(str);
        code707.arrange(str);
        System.out.println(cnt);
    }


    private void partion(char [] str, int idx, int len) {
        if(idx == len) {
            cnt++;
            System.out.println(Arrays.toString(str));
        }

        for(int i=idx; i<len; i++) {
            swap(str,i,idx);
            partion(str,idx+1,len);
            swap(str,i,idx);
        }

    }


    private void combineGroup(char [] str) {
        if (str == null) return;
        int len = str.length;
        if (len == 0) return;

        LinkedList<Character> tmpList = new LinkedList<>();
        for(int i=1; i<=len; i++) {
            combineGroup(str, tmpList, i, 0, len);
        }
    }

    private  void combineGroup(char [] str, LinkedList<Character> tmpList, int number, int index, int len ) {
        if(number == 0) {
            cnt++;
            System.out.println(tmpList.toString());
            return;
        }
        if(index == len) return;

        tmpList.add(str[index]);
        combineGroup(str,tmpList,number-1,index+1, len);

        tmpList.removeLast();
        combineGroup(str,tmpList,number, index+1, len);

    }


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



}
