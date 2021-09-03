package top.yms.recent.c202108;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class FileRead {
    private static void getRead(String path) throws Exception{
        FileReader fileReader = new FileReader(path);
        int c = -1;
        char [] chars = new char[1024];
        while ((c = fileReader.read(chars)) != -1) {
            System.out.print(chars);
        }
        fileReader.close();
    }

    private static void readeFile(String path) throws Exception {
        InputStreamReader isr = new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8);

        int len = -1;
        char [] bytes = new char[1024];
        while ((len = isr.read(bytes)) != -1) {
            System.out.print(bytes);

        }
        isr.close();

    }

    public static void main(String[] args) throws Exception{

        PrintStream printStream = new PrintStream("D:\\tmp\\print.txt");
        System.setOut(printStream);

        String path = "G:\\Project\\Go\\MyTools\\local\\find\\FindFileTools.go";
       readeFile(path);
        System.out.println("What hello this");
       printStream.close();

        //getRead(path);
    }


}
