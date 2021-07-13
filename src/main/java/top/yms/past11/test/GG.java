package top.yms.past11.test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GG {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);

            Socket accept = serverSocket.accept();
            int read = accept.getInputStream().read();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
