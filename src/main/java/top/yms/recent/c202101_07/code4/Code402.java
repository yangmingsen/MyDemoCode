package top.yms.recent.c202101_07.code4;

public class Code402 {
    public static void main(String[] args) {
       String str ="1;2;3;4;6";
        String[] split = str.split(";");
        for(String ss : split) {
            System.out.println(ss);
        }

    }
}
