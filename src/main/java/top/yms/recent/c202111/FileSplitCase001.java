package top.yms.recent.c202111;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 大文件 分片 和 合并
 */
public class FileSplitCase001 {


    static class SplitFile implements Callable<SplitFile> {
        private String targetName;
        private int byteSize;
        private byte[] data;
        private String filePath;
        private long startPos;
        private int di;

        private void doCreateTargetFile() throws IOException {
            String targetName = this.targetName;
            File file = new File(targetName);
            boolean newFile = file.createNewFile();

            FileOutputStream fos = new FileOutputStream(file);
            for(int i=0; i<byteSize; i++) {
                fos.write(data[i]);
            }
            fos.close();
        }

        public int getByteSize() {
            return byteSize;
        }

        public byte[] getData() {
            return data;
        }

        public SplitFile reset(long startPos) {
            di = 0;
            this.startPos = startPos;

            return this;
        }

        public SplitFile(String filePath, int byteSize, long startPos, String targetName) {
            this.byteSize = byteSize;
            this.filePath = filePath;
            this.startPos = startPos;
            this.targetName = targetName;
            this.data = new byte[byteSize];
            this.di = 0;
        }

        public void doGetData() throws Exception{
            RandomAccessFile randomAccessFile = new RandomAccessFile(filePath, "r");
            randomAccessFile.seek(startPos); //移动指针到每“段”开头
            randomAccessFile.readFully(data);
            randomAccessFile.close();
        }


        @Override
        public String toString() {
            return "SplitFile{" +
                    "targetName='" + targetName + '\'' +
                    ", byteSize=" + byteSize +
                    ", filePath='" + filePath + '\'' +
                    ", startPos=" + startPos +
                    ", di=" + di +
                    '}';
        }

        public void run() {
            try {
                doGetData();
                doCreateTargetFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public SplitFile call() throws Exception {
            run();
            return this;
        }
    }



    public void doSplit(File file) throws Exception {
        long fileLen = file.length();
        List<Section> sections = compSection(fileLen, 8);


    }
    /**
     * 大文件分片传输原理：
     *  为了实现大文件快速传输，可以考虑使用多任务并行方式发送。 也就是将一个大文件分切成多个等份的小文件，然后同时传输。
     *  接收方收到分片文件后不立即进行合并，而是先建立本地临时文件存放本地文件系统中，待所有分片文件到达完毕后再进行合并。
     *
     *  实现注意：
     *      1. 分切程序应该注意分切大小，不宜过大，不然容易造成内存膨胀。
     *      2. 不宜一次性将所有分片程序读入内存，不然会内存爆炸（如果一个大文件有16G,而内存只有8G,很显然会出问题)，可一次性只读入几个分片，后续轮替
     *      3. 存储实现时，考虑复用装载数据程序（意思是读取数据的程序可以复用，比如A程序读入的了一个分片a，当分片发送完毕后。
     *          不用立即释放这个程序内存，可以继续用这个程序的内存读取分片b).
     *      
     *
     *
     *
     * 实现：
     *     1.确保Client端发送的文件必须全部到达Server端.
     *     2.Client端必须进行发送统计。也就是没发送过去一个分片,必须收到Server端的成功信息回复; 这时必须进行时间限制等待,
     *          如果在5秒(暂时定5s)内没有收到Server的回应或者发送其他异常,那么认为失败，断开当前连接，重新建立新连接，重新传输该分片。
     *     3.Server端收到分片请求数据后,立即写入本地，传输完毕后响应成功信息。 如果遇到异常断开当前连接，删除当前临时分片文件。
     *     4.
     * 公共：
     *   class
     *
     * 服务端：
     *  class
     *
     *
     */


    /**
     * 分片程序
     */
    @Test
    public void testRandomSplit() {
        String filePath = "D:\\tmp\\split\\yrecv.exe";
        File file = new File(filePath);
        int core = 4;

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        System.out.println("SrcFileLen = "+file.length());
        List<Section> sections = compSection(file.length(), core);

        List<Future<SplitFile>> futureList = new LinkedList<>();

        String targetName = "D:\\tmp\\split\\yrecv";
        for(int i=0; i<sections.size(); i++) {

            Section stn = sections.get(i);
            System.out.println(stn);

            String tmpTargetName = targetName+i;
            int byteSize = Integer.parseInt(new Long(stn.end-stn.start).toString());
            long startPos = stn.start;



            FutureTask<SplitFile> futureTask = new FutureTask(new SplitFile(filePath, byteSize, startPos, tmpTargetName));
            executorService.execute(futureTask);

            futureList.add(futureTask);
        }

        for(Future<SplitFile> future : futureList) {
            try {
                SplitFile theSplitInfo = future.get();
                System.out.println(theSplitInfo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();

    }



    //临时文件格式 /home/yms/data/xx001
    public List<String> fileList(String srcPath, int size) {
        List<String> list = new LinkedList<>();

        for(int i=0; i<size; i++) {
            list.add(srcPath+i);
        }
        return list;
    }



    // 合并
    @Test
    public void testMerge() {
        String srcPath = "D:\\tmp\\split\\yrecv";
        int size = 4;
        List<String> pathList = fileList(srcPath, size);

        String targetPath = "D:\\tmp\\split\\NewYrecv.exe";
        try {
            FileOutputStream fos = new FileOutputStream(targetPath);

            for(String splitName : pathList) {
                FileInputStream fis = new FileInputStream(splitName);

                int rd = -1;
                byte [] bytes = new byte[8];
                while ((rd = fis.read(bytes)) > 0 ) {
                    fos.write(bytes,0, rd);
                }

                fis.close();

                File file = new File(splitName);
                file.delete();
            }

            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testCompSection() {
        int core = 4;
        long fileSize = 16*1024*1024*1024L;
        compSection(fileSize, core).stream().forEach(System.out::println);
    }

    public  List<Section> compSection(long total, int core) {
        long maxLen = 50 * 1024  * 1024L; //50M

        long avg = 0L;
        while (true ) {
            avg = total/core;
            if (avg <= maxLen) break;
            core++;
        }

        long sp = 0;
        List<Section> list = new ArrayList<>();
        for(int i=0; i<core; i++) {
            if (i+1!=core) {
                list.add(new Section(sp,sp+avg));
                sp = sp+avg;
            } else {
                list.add(new Section(sp, sp+(total-sp)));
            }
        }

        return list;
    }

    static class Section {
        long start;
        long end;

        public Section(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Section{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }


    @Test
    public void test() {
        File file = new File("G:\\Project\\Java\\other\\MyDemoCode\\src\\main\\java\\top\\yms\\recent\\CurrencyParam.java");
        try {
            doSplit(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
