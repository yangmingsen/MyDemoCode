package top.yms.recent.code7;

import java.util.Arrays;

public class Code708 {

    public static void main(String[] args) {
        char [] str = {'a','b','c'};
        Code708 code708 = new Code708();
        code708.arrange(str);
    }

    private void arrange(char [] str) {
        doArrange(str,0, str.length);
    }

    void swap(char [] str, int i, int j) {









        char tmp = str[i];
        str[i]=str[j];
        str[j]=tmp;
    }

    private void doArrange(char [] str, int index, int len) {
        if(index == len) {
            System.out.println(Arrays.toString(str));
        }
        for(int i = index; i<len; i++) {
            swap(str, i, index);
            doArrange(str, index+1, len);
            swap(str, i, index);
        }
    }

}
