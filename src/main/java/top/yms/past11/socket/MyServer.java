package top.yms.past11.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(9000);

        while (true) {
            Socket accept = serverSocket.accept();

            new Thread( () -> {
                try {
                    InputStream inputStream = accept.getInputStream();
                    //BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

                    StringBuffer stringBuffer = new StringBuffer();
                    byte [] bytes = new byte[1024];
                    int rdx = 0;
                    while ((rdx = inputStream.read(bytes)) > 0) {
                        stringBuffer.append(new String(bytes));
                        System.out.println("buf ="+stringBuffer.toString());
                    }
                    System.out.println(stringBuffer.toString());

                    String out = "I recv";
                    accept.getOutputStream().write(out.getBytes());
                    accept.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }).start();

        }

    }
}
