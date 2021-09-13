package top.yms.recent.c202108;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class LFTServer {

    private static ExecutorService executorService = Executors.newCachedThreadPool();

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


    private static String readMsgFromStream(InputStream is, byte []bytes) {
        int len = 0;
        try {
            int num = -1;
            while ((num = is.read()) > 0) {
                if (num == 10) { //如果是换行
                    break;
                } else {
                    bytes[len++] = (byte) num;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(bytes, 0, len, StandardCharsets.UTF_8);
    }

    private static String readLongMsg(InputStream is) {
        byte[] bytes = new byte[200];
        return readMsgFromStream(is, bytes);
    }


    private static String readShortMsg(InputStream is) {
        byte[] bytes = new byte[20];
        return readMsgFromStream(is, bytes);
    }

    private static byte[] wrapData(Object sendMsg) {
        String sMsg = "";
        if (sendMsg instanceof  Integer) {
            sMsg = ((Integer)sendMsg).toString();
        } else if (sendMsg instanceof Long) {
            sMsg = ((Long)sendMsg).toString();
        } else {
            sMsg = (String)sendMsg;
        }

        return (sMsg+"\n").getBytes(StandardCharsets.UTF_8);
    }

    private static void doDirHandler(Socket accept) {
        try {
            OutputStream os = accept.getOutputStream();
            InputStream is = accept.getInputStream();

            //发送类型响应
            os.write(wrapData("ok"));


            //接收长度信息

            String rcvMsg = readShortMsg(is);
            System.out.println("接收到长度数据：" + rcvMsg);
            int msgLen = Integer.parseInt(rcvMsg);

            //发送接收到长度数据响应
            os.write(wrapData("ok"));

            byte[] dataBytes = new byte[1024];
            int dLen = -1;
            StringBuilder tmpStr = new StringBuilder();
            int total = 0;
            while ((dLen = is.read(dataBytes)) > -1) {
                tmpStr.append(new String(dataBytes, 0, dLen, StandardCharsets.UTF_8));
                total += dLen;
                if (total == msgLen) {
                    break;
                }
            }
            //发送接收到dir数据响应
            os.write(wrapData("ok"));

            //建立目录
            String[] dirArray = tmpStr.toString().split("\n");
            for (String dirName : dirArray) {
                String cDir = replaceSeparator(dirName);
                System.out.println("cDir="+cDir);
                new File(cDir).mkdirs();
            }

            //创建目录

            os.close();
            is.close();
            accept.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void doFileHandler(Socket accept) {

        try {
            InputStream nis = accept.getInputStream();
            OutputStream nos = accept.getOutputStream();

            String ok = "ok";
            while (true) {

                //获取传输标志
                String rcvMsg = readShortMsg(nis);
                //System.out.println("收到Client传输标志 => "+rcvMsg);
                if (rcvMsg.equals("c")) { //如果收到结束 则break
                    break;
                }

                //获取文件路径
                rcvMsg = readLongMsg(nis);
                String filePath = replaceSeparator(rcvMsg); //处理文件夹分隔符

                //获取文件长度
                rcvMsg = readShortMsg(nis);
                long fileLen = Long.parseLong(rcvMsg);

                //响应Client 准备传输文件流
                nos.write(wrapData("ok1"));

                //接受文件流

                System.out.println("正在接收文件【"+filePath+"】 长度【"+fileLen+"】");
                if (fileLen != 0L) {
                    FileOutputStream fos = new FileOutputStream(filePath);
                    byte [] dataBytes = new byte[1024];
                    int tmpLen = -1;
                    long cnt = 0;
                    while ((tmpLen = nis.read(dataBytes)) > 0) {
                        fos.write(dataBytes, 0, tmpLen);
                        cnt += tmpLen;
                        //System.out.println("已接收长度="+cnt+" 总长度="+fileLen);
                        if (cnt >= fileLen) {
                            break;
                        }
                    }
                    fos.close();
                } else {
                    File newF = new File(filePath);
                    boolean newFile = newF.createNewFile();
                    if (newFile) {
                        System.out.println(filePath+" =>创建成功");
                    } else {
                        System.out.println(filePath+" =>创建失败");
                    }
                }
                int total = recvTotal.getAndIncrement();

                //响应Client 传输文件流完毕
                nos.write(wrapData("ok2"));
                System.out.println("【"+filePath+"】 => 传输完成");
                System.out.println();

            }
            nos.close();
            nis.close();
            accept.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void doServer() throws Exception {

        ServerSocket serverSocket = new ServerSocket(port);

        while (true) {
            final Socket accept = serverSocket.accept();
            InputStream is = accept.getInputStream();

            String rMsg = readShortMsg(is);
            System.out.println("接收到Client：" + accept.getRemoteSocketAddress().toString()+" 请求类型：" + rMsg);

            if (rMsg.equals("d")) {
                executorService.execute(() -> doDirHandler(accept));
            } else if (rMsg.equals("f")) {
                executorService.execute(() -> doFileHandler(accept));
            } else {
                throw new Exception("非法字符");
            }

        }


    }


    private static int port = 8848;

    private static AtomicInteger recvTotal = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {
        doServer();
    }
}
