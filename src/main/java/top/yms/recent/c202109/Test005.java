package top.yms.recent.c202109;

import java.io.File;
import java.io.FileInputStream;

public class Test005 {



    public static void main(String[] args) throws Exception{

        String 文件路径 = "G:\\Project\\Go\\MyTools\\network\\ysend\\ysend.go";
        File 文件 = new File(文件路径);

        long 文件长度 = 文件.length();

        System.out.println("文件长度="+文件长度);


    }
}
