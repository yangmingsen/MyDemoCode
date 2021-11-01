package top.yms.recent.c202108;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class LFTClient {


    //BFS 查找所有文件
    private static List<File> getFileList(String path) {
        List<File> listPath = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(path);
        listPath.add(new File(path));
        while (!queue.isEmpty()) {
            String filePath = queue.poll();
            File file = new File(filePath);

            if (file.isDirectory()) {
                for (File tmpFile : Objects.requireNonNull(file.listFiles())) {
                    listPath.add(tmpFile);
                    if (tmpFile.isDirectory()) {
                        queue.add(tmpFile.getPath());
                    }
                }
            }
        }

        return listPath;
    }

    private static void printPath(String path) throws Exception {
        File file = new File(path);
        for (File tmpFile : Objects.requireNonNull(file.listFiles())) {
            if (tmpFile.isDirectory()) {
                printPath(tmpFile.getPath());
            } else {
                System.out.println(tmpFile.getPath());
            }
        }
    }

    private static void showBar() {
        int last = 0;
        while (true) {
            StringBuilder tmpStr = new StringBuilder();
            int current = finshNum.get();
            int total = totalNum;

            int len = current - last;
            last = current;

            double percent = (current * 1.0d / total) * 100;


            for(int i=0; i<(int)(percent)/2; i++) {
                tmpStr.append("#");
            }
            System.out.printf("\r总进度[%-50s] => %.2f%% => %s个文件/s",tmpStr.toString(), percent, len+"");
            if (current >= total) {
                break;
            }
            try {
                Thread.sleep(1*1000);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
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

    private static void syncDir(List<File> dirs) throws Exception {

        StringBuilder tmpStr = new StringBuilder();
        int len = dirs.size();
        for (int i = 0; i < len; i++) {
            if (i + 1 == len) {
                tmpStr.append(dirs.get(i).getPath());
            } else {
                tmpStr.append(dirs.get(i).getPath()).append("\n");
            }
        }
        int strLen = tmpStr.length();
        byte[] dataBytes = tmpStr.toString().getBytes(StandardCharsets.UTF_8);

        //建立Socket Connection
        Socket cs = new Socket(remoteIp, port);

        OutputStream os = cs.getOutputStream();
        InputStream is = cs.getInputStream();

        //发送类型
        os.write(wrapData("d"));


        //接收响应信息
        String rcvMsg = readShortMsg(is);
        System.out.println("接收到请求类型响应：" + rcvMsg);


        //发送长度
        os.write(wrapData(new Integer(strLen)));

        //接收响应信息
        rcvMsg = readShortMsg(is);
        System.out.println("接收到长度响应：" + rcvMsg);
        //这里要考虑是否进行验证判断

        //发送数据
        os.write(dataBytes);
        //接收响应信息
        rcvMsg = readShortMsg(is);
        System.out.println("接收到数据响应：" + rcvMsg);

        //关闭流
        os.close();
        is.close();
        cs.close();
    }

    private static void syncFile(final List<File> files) {
        final int totalLen = files.size();
        totalNum = totalLen;
        finshNum = new AtomicInteger(0);

        executorService.execute(() -> {
            blockingQueue = new ArrayBlockingQueue<>(totalLen);
            for (File file : files) {
                try {
                    blockingQueue.put(file);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        executorService.execute(() -> showBar());

        for (int i = 0; i < threadNum; i++) {
            final int barIdx = i;
            executorService.execute(() -> {
                try {
                    //建立网络连接
                    Socket cs = new Socket(remoteIp, port);
                    InputStream nis = cs.getInputStream();
                    OutputStream nos = cs.getOutputStream();

                    //发送文件类型
                    nos.write(wrapData("f"));

                    while (true) {
                        int size = blockingQueue.size();
                        if (size == 0) {
                            System.out.println("====传输结束====");
                            nos.write(wrapData("c"));
                            break;
                        }
                        //开始传输
                        nos.write(wrapData("s"));

                        File file = blockingQueue.poll(3, TimeUnit.SECONDS);
                        long fileLen = file.length(); //获取文件长度
                        String fileName = file.getName(); //获取文件path

                        //初始化进度条
//                        BarInfo barInfo = new BarInfo(barIdx, fileName, 0L, fileLen);
//                        barInfos[barIdx] = barInfo;


                        //发送路径
                        String filePath = file.getPath() ;
                        nos.write(wrapData(filePath));

                        //发送文件长度
                        String fileLenStr = fileLen + "";
                        nos.write(wrapData(fileLenStr));


                        String rcvMsg = readShortMsg(nis);
                        //System.out.println("接收到Server请求传输文件响应：" + rcvMsg+"  准备发送文件"+file.getPath()+" 文件长度["+fileLenStr);

                        //读取本地文件
                        if (fileLen != 0L) {
                            FileInputStream fis = new FileInputStream(file);
                            byte[] tmpBytes = new byte[1024];
                            int tmpLen = -1;
                            while ((tmpLen = fis.read(tmpBytes)) > 0) {
                               // barInfo.current += tmpLen;
                               // System.out.println("读取到文件长度【"+tmpLen+"】 已发送长度"+barInfo.current+" 总长度【"+fileLenStr);
                                nos.write(tmpBytes, 0, tmpLen);
                            }
                            fis.close();
                        }


                        int sendNum = finshNum.getAndIncrement();
                        rcvMsg = readShortMsg(nis);
                       // System.out.println("接收到Server传输文件完成响应：" + rcvMsg+" =>文件 "+file.getPath()+" 传输完成"+"=>已发送文件"+file.getPath()+"已发送文件量："+sendNum);
                        //System.out.println();


                    }

                    //关闭网络流
                    nos.close();
                    nis.close();
                    cs.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            });
        }


    }


//    private static void printBar() {
//        BarInfo[] barInfos = LFTClient.barInfos;
//        for (BarInfo info : barInfos) {
//            double pencent = (info.current * 1.0d) / (info.total * 1.0d);
//
//            System.out.printf("\r " + info.name + " (" + pencent + ")%");
//        }
//    }
//
//
//    static class BarInfo {
//        public int id;
//        public String name;
//        public long current;
//        public long total;
//        public long last;
//
//        public BarInfo(int id, String name, long current, long total) {
//            this.id = id;
//            this.name = name;
//            this.current = current;
//            this.total = total;
//            this.last = current;
//        }
//    }


    private static String remoteIp = "192.168.25.71";
    private static int port = 8848;
    private static ArrayBlockingQueue<File> blockingQueue = null;
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    private static int threadNum = 1;
    //private static BarInfo[] barInfos = new BarInfo[threadNum];

    private static AtomicInteger finshNum = null;
    private static int totalNum = -1;

    public static void main(String[] args) throws Exception {
       // String path = "./src/main/java/top/yms/recent/c202101_07/cocd5";
        //String path = "./src/main/java/top/yms/recent";
        System.out.println(Arrays.toString(args));

        System.out.println(args.length);
        if (args.length < 2) {
            System.out.println("参数错误: ./* remoteIp [threadNum]");
            System.exit(-1);
        }


        //String path = "./src";
        String path = args[0];

        //远程
        remoteIp = args[1];


        //线程数
        if (args.length >= 3) {
            threadNum = Integer.parseInt(args[2]);
        }


        List<File> fileList = getFileList(path);


        List<File> f = new ArrayList<>();
        List<File> dir = new ArrayList<>();

        for (File t : fileList) {
            if (t.isDirectory()) {
                dir.add(t);
            } else {
                f.add(t);
            }
        }
        System.out.println("文件数量="+f.size());
        System.out.println("目录数量="+dir.size());

        syncDir(dir);
        System.out.println("同步dir完成。。。");
        syncFile(f);



    }

}
