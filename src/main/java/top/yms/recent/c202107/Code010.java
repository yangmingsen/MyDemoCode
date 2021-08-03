package top.yms.recent.c202107;

public class Code010 {
    public static void main(String[] args) {
        String str = "012345abcABC";
        byte[] bytes = str.getBytes();
        for(byte b : bytes) {
            System.out.print(b+",");
        }
    }
}
