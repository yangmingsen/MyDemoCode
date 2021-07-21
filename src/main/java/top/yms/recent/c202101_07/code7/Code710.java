package top.yms.recent.c202101_07.code7;

import java.util.Arrays;
import java.util.LinkedList;

public class Code710 {

    private void doCombineGroup(char [] str, LinkedList<Character> tmpList, int number, int index, int len) {
        if(number == 0) {
            System.out.println(tmpList.toString());
            return;
        }
        if (index == len) return;

        tmpList.add(str[index]);
        doCombineGroup(str,tmpList,number-1,index+1,len);

        tmpList.removeLast();
        doCombineGroup(str, tmpList, number, index+1, len);
    }

    private void combineGroup(char [] str) {
        if (str == null) return;
        int len = str.length;
        if (len == 0) return;






        LinkedList<Character> tmpList = new LinkedList<>();













        for(int i=1; i<=len; i++) {
            doCombineGroup(str, tmpList, i, 0, len);
        }
    }

    public static void main(String[] args) {
        Code710 code710 = new Code710();
      //  code710.combineGroup(new char[]{'a','b','c','d'});
        code710.arrange(new char[]{'a','b','c','d'});
    }

    private void doArrange(char [] str, int index, int len) {
        if (index == len) {
            System.out.println(Arrays.toString(str));
        }
        for(int tmpIndex = index; tmpIndex < len; tmpIndex++) {
            swap(str,tmpIndex,index);
            doArrange(str, index+1, len);
            swap(str,tmpIndex,index);
        }
    }

    private void arrange(char [] str) {
        if (str == null) return;
        int len = str.length;
        if (len == 0) return;
        doArrange(str,0,len);
    }

    void swap(char [] str, int i, int j) {
        char tmp = str[i];
        str[i]=str[j];
        str[j]=tmp;
    }


}
