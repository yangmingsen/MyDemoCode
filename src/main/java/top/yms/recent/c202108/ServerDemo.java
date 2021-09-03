package top.yms.recent.c202108;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            ServerSocket ss = new ServerSocket(8848);
            System.out.println("启动服务器....");
            while (true) {
                Socket s = ss.accept();
                System.out.println("客户端:"+s.getInetAddress().getLocalHost()+"已连接到服务器");
                SocketAddress localSocketAddress = s.getLocalSocketAddress();
                System.out.println("localSocketAddress"+localSocketAddress);

                InetAddress localAddress = s.getLocalAddress();
                System.out.println("localAddress="+localAddress);

                InetAddress inetAddress = s.getInetAddress();
                System.out.println("inetAddress="+inetAddress);

                String hostAddress = s.getInetAddress().getHostAddress();
                System.out.println("hostAddress="+hostAddress);

                String hostName = s.getInetAddress().getHostName();
                System.out.println("hostName="+hostName);


                SocketAddress remoteSocketAddress = s.getRemoteSocketAddress();
                System.out.println("remoteSocketAddress="+remoteSocketAddress);

                System.out.println("chnne="+s.getChannel());

                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            while (true) {
                                BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                                //读取客户端发送来的消息
                                String mess = br.readLine();

                                if (mess == null) {
                                    System.out.println("Close");
                                    break;
                                }
                                System.out.println("客户端："+mess);
                                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                                bw.write(mess+"\n");
                                bw.flush();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });


            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
