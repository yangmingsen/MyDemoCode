package top.yms.recent.code4;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Code402 {
    public static void main(String[] args) {
       String str ="1;2;3;4;6";
        String[] split = str.split(";");
        for(String ss : split) {
            System.out.println(ss);
        }

    }
}
