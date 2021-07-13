package top.yms.past11.socket;

import java.net.Socket;

public class MyClient {
    public static void main(String[] args) throws Exception {
        Socket localhost = new Socket("localhost", 9000);
        localhost.getOutputStream().write(new String("helloworld").getBytes());
        localhost.getOutputStream().write(new String("helloworld").getBytes());

        StringBuilder stringBuilder = new StringBuilder();
        byte [] buf = new byte[1024];
        int i = 0;
        while ((i = localhost.getInputStream().read(buf)) > 0) {
            stringBuilder.append(buf);
            System.out.println(stringBuilder.toString());
        }

        localhost.close();

    }
}
