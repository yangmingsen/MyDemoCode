package top.yms.recent.c202109;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Test002 {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8848);

        while (true) {
            Socket accept = serverSocket.accept();
            InputStream inputStream = accept.getInputStream();
            OutputStream outputStream = accept.getOutputStream();

            byte [] bytes = new byte[32];
            System.out.println("读取Client数据结果中...");
            int read = inputStream.read(bytes);
            String s = new String(bytes, 0, read);
            System.out.println("s ="+s);
            int total = Integer.parseInt(s);
            System.out.println("发送确认数据中1...");
            outputStream.write("y".getBytes());

            byte[] bytes1 = new byte[1024];

            int cnt = 0;
            int len = -1;
            StringBuilder tmpStr = new StringBuilder();
            System.out.println("读取Client数据结果中2...");
            while ((len = inputStream.read(bytes1)) > -1) {
                System.out.println("收到数据长度:"+len);
                String s1 = new String(bytes1, 0, len);
                //System.out.println("收到数据"+s1);
                tmpStr.append(s1);
                cnt+=len;

                if (cnt == total) {
                    break;
                }
            }
            //System.out.println("tmpStr="+tmpStr.toString());
            outputStream.write("y".getBytes());

            String[] split = tmpStr.toString().split("\n");
            for(String str : split) {
                System.out.println("str="+str);
            }


        }

    }
}
