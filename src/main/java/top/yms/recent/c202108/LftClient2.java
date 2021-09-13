package top.yms.recent.c202108;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class LftClient2 {
    private static void doClient() throws Exception {
        //1. 得到一个网络通道
        SocketChannel channel=SocketChannel.open();
        //2. 设置非阻塞方式
        channel.configureBlocking(false);
        //3. 提供服务器端的 IP 地址和端口号
        InetSocketAddress address=new InetSocketAddress("127.0.0.1",9949);
        //4. 连接服务器端
        if(!channel.connect(address)){
            while(!channel.finishConnect()){ //nio 作为非阻塞式的优势
                System.out.println("Client:连接服务器端的同时，我还可以干别的一些事情");
                Thread.sleep(1*1000);
            }
        }
        //5. 得到一个缓冲区并存入数据
        String msg="hello,Server";
        ByteBuffer writeBuf = ByteBuffer.wrap(msg.getBytes());
        //6. 发送数据
        channel.write(writeBuf);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("input: ");
            String s = scanner.nextLine();
            if (s.equals("exit")) {
                break;
            }

            ByteBuffer wrapMsg = ByteBuffer.wrap(s.getBytes());
            channel.write(wrapMsg);

        }
        channel.close();

    }

    public static void main(String[] args) throws Exception{
        doClient();
    }
}
