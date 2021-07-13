package top.yms.net;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientDemo {
    public static void main(String[] args) throws Exception {
        System.out.println("客户端 发送数据");
// 1.创建 Socket ( ip , port ) , 确定连接到哪里.
        Socket client = new Socket("localhost", 6666);
// 2.通过Scoket,获取输出流对象
        OutputStream os = client.getOutputStream();
        int cc = 10;
        while (cc < 99) {
            String str = "你好你好";
            os.write(str.getBytes("utf-8"));
            cc++;
        }
        os.write("the end of".getBytes());
// 3.写出数据.
        // ==============解析回写=========================
        // 4. 通过Scoket,获取 输入流对象
        InputStream in = client.getInputStream();
        // 5. 读取数据数据
        byte[] b = new byte[100];
        int len = in.read(b);
        System.out.println(new String(b, 0, len));
// 6. 关闭资源 .
        in.close();
        os.close();
        client.close();
    }
}
