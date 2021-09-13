package top.yms.recent.c202108;

import java.io.File;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

public class LftServer2 {

    private static int port = 9949;

    private static AtomicInteger recvTotal = new AtomicInteger(0);

    private static String replaceSeparator(String path) {
        String separator = File.separator;

        StringBuilder tmpStr = new StringBuilder();
        String[] split = null;
        if (path.indexOf("\\") > 0) {
            split = path.split("\\\\");
        } else {
            split = path.split("/");
        }
        for(String tt : split) {
            tmpStr.append(tt).append(separator);
        }

        return tmpStr.toString();
    }

    private static void doServer() throws Exception {
        //1. 得到一个 ServerSocketChannel 对象 老大
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //2. 得到一个 Selector 对象 间谍
        Selector selector = Selector.open();
        //3. 绑定一个端口号
        serverSocketChannel.bind(new InetSocketAddress(port));
        //4. 设置非阻塞方式
        serverSocketChannel.configureBlocking(false);
        //5. 把 ServerSocketChannel 对象注册给 Selector 对象
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);


        while(true) {
            //6.1 监控客户端
            if (selector.select(2000) == 0) { //nio 非阻塞式的优势
                System.out.println("Server:没有客户端搭理我，我就干点别的事");
                continue;
            }
            //6.2 得到 SelectionKey,判断通道里的事件
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                if (key.isAcceptable()) { //客户端连接请求事件
                    System.out.println("OP_ACCEPT");
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                if (key.isReadable()) { //读取客户端数据事件
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    channel.read(buffer);

                }
                // 6.3 手动从集合中移除当前 key,防止重复处理
                keyIterator.remove();
            }
        }



    }


    public static void main(String[] args) throws Exception{
        doServer();
    }


}
