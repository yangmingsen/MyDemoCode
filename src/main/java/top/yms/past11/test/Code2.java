package top.yms.past11.test;

import java.io.File;
import java.io.FileInputStream;

public class Code2 {
    protected static void yms() {
        try {
            FileInputStream fis = new FileInputStream(new File("E:\\Project\\Java\\top.yms.past11.test.HelloTest\\src\\main\\java\\top\\yms\\Code2.java"));

            byte [] bytes = new byte[512];
            while (fis.read(bytes) > 0) {
                String s = new String(bytes);
                System.out.println(s);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        yms();
    }

}
