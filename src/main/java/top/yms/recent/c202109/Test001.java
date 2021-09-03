package top.yms.recent.c202109;


import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Test001 {
    public static void main(String[] args) throws Exception{

        Socket s = new Socket("10.18.21.37",8848);
        StringBuilder tmpStr = new StringBuilder();
        int len = 10;
        for(int i=0; i<len; i++) {
            if (i+1 == len)
                tmpStr.append("./src/main/Code00").append(i).append(".java");
            else
                tmpStr.append("./src/main/Code00").append(i).append(".java\n");
        }
        int length = tmpStr.length();
        OutputStream outputStream = s.getOutputStream();
        InputStream inputStream = s.getInputStream();

        outputStream.write((length+"").getBytes());
        System.out.println("发送长度成功");

        byte [] bytes = new byte[32];
        System.out.println("读取发送长度结果中...");
        int len1 = inputStream.read(bytes);
        String str = new String(bytes, 0, len1);
        System.out.println("res = "+str);

        System.out.println("发送数据中...");
        outputStream.write(tmpStr.toString().getBytes());
        outputStream.flush();

        System.out.println("读取发送数据结果中...");
        int len2 = inputStream.read(bytes);
        String str2 = new String(bytes, 0, len2);
        System.out.println("res2 = "+str2);



        s.close();


    }
}
