package top.yms.recent.c202109;

import java.io.File;
import java.io.FileInputStream;

public class Test005 {



    public static void main(String[] args) throws Exception{

        String filePath = "G:\\Project\\Go\\MyTools\\network\\ysend\\ysend.go";
        File file = new File(filePath);
        long length = file.length();
        System.out.println("file="+length);

    }
}
