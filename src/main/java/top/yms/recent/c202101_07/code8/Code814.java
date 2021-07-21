package top.yms.recent.c202101_07.code8;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class Code814 {





    public static void main(String[] args) throws UnsupportedEncodingException {
        byte [] bytes = new byte[32];
        for(byte i = 0; i<25; i++) {
            bytes[i] = (byte) (65+i);
        }

        String str = new String(bytes, "UTF-8");
        System.out.println(str);

        System.out.println(Arrays.toString(bytes));



    }
}
