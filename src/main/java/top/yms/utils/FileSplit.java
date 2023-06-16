package top.yms.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class FileSplit {
    private static final int CORE_SIZE = Runtime.getRuntime().availableProcessors();
    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(CORE_SIZE, 2*CORE_SIZE, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
    private static final String SPLIT_FLAG = "___";
    private static final long B = 1L;
    private static final long KB = 1024L * B;
    private static final long MB = KB * KB;
    private static final long GB = MB * KB;
    private static final long SIZE_LIMIT = 10 * MB;

    private static final DecimalFormat decimalFormat= new DecimalFormat( ".00" ); //构造方法的字符格式这里如果小数不足2位,会以0补足.

    private FileSplit() {
    }


    /**
     * merge method
     *
     * @param src 源合并目录， 必须目录
     */
    public static void doMerge(String src) {
        List<MergeFile> mgList = getMergeFileList(src);
        if (mgList.size() < 1) {
            System.out.println("空目录...");
            return;
        }
        mgList.sort(Comparator.comparingInt(o -> o.id));
        int corePoolSize = executor.getCorePoolSize();
        //add mul thread merge
        mgList = mulThrMerge(mgList, corePoolSize, src);
        String baseName = mgList.get(0).file.getName().split(SPLIT_FLAG)[0];
        String mgName = src + baseName;
        doStream(mgName, mgList);
        System.out.println("Merge Ok .....");
    }

    private static List<MergeFile> mulThrMerge(List<MergeFile> mgList, int corePoolSize, String src) {
        try {
            int mgSize = mgList.size();
            int len = mgSize / corePoolSize;
            final CountDownLatch cdl = new CountDownLatch(corePoolSize);
            List<Future<MergeFile>> futureList = new LinkedList<>();
            for (int i = 0; i < corePoolSize; i++) {
                final int id = i+mgSize+1;
                Future<MergeFile> future;
                List<MergeFile> mergeFiles;
                if (i + 1 == corePoolSize) {
                    mergeFiles = mgList.subList(i * len, mgSize);
                } else {
                    mergeFiles = mgList.subList(i * len, i * len + len);
                }
                future = executor.submit(() -> doMulMerge(src, mergeFiles, id, cdl));
                futureList.add(future);
            }
            cdl.await();

            return futureList.stream().map(f -> {
                try {
                    return f.get();
                } catch (Exception e) {
                    e.printStackTrace();
                    return new MergeFile();
                }
            }).filter(m -> m.file != null).sorted((Comparator.comparingInt(o -> o.id))).collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }

    private static boolean doStream(String target, List<MergeFile> mgList) {
        System.out.println(Thread.currentThread().getName()+"合并Name=> " + target);
        try (FileOutputStream fos = new FileOutputStream(target)) {
            byte[] buf = new byte[512];
            for (MergeFile mf : mgList) {
                FileInputStream fis = new FileInputStream(mf.file);
                int n = -1;
                while ((n = fis.read(buf)) > 0) {
                    fos.write(buf, 0, n);
                }
                fis.close();
                mf.file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static MergeFile doMulMerge(String src, List<MergeFile> mgList, int id, CountDownLatch cdl) {
        String baseName = src + mgList.get(0).file.getName().split(SPLIT_FLAG)[0] + SPLIT_FLAG + id;
        MergeFile mergeFile = new MergeFile();
        mergeFile.id = id;
        if (doStream(baseName, mgList)) {
            mergeFile.file = new File(baseName);
        }
        cdl.countDown();
        return mergeFile;
    }

    /**
     * split method
     *
     * @param src  源文件, must 文件
     * @param dest 目标输出目录 must 目录
     */
    public static void doSplit(String src, String dest) {
        File file = new File(src);
        if (!file.exists()) {
            throw new RuntimeException("源文件不存在...");
        }
        File destDir = new File(dest);
        if (!destDir.exists() && !destDir.isDirectory()) {
            System.out.println("创建文件夹==>" + dest);
            destDir.mkdirs();
        }

        long fileSize = file.length();

        List<Slice> fileSlices = getSlice(fileSize);
        //切分
        doFileSplit(fileSlices, file, dest);

        System.out.println("OK Split Success");
    }

    public static SingleFile suggestionSize(long size, String arg) {
        int coreNum = CORE_SIZE;
        int sliceNum = 0;
        SingleFile singleFile = new SingleFile();
        singleFile.srcSize = size;
        if (size < SIZE_LIMIT) {
            singleFile.isEqualSzie=false;
            singleFile.sliceSize = (int)size;
            singleFile.sliceNum = 1;
            System.out.println("不分隔大小=> " + size + " 分隔数=>" + 1 + " 大小=> " + printSize(size));

            return singleFile;
        }
        boolean isEqualSplit = isPrime(size) || isLimitSplit(size);
        if (isEqualSplit) {
            for (int i = coreNum; ; i++) {
                long singleSize = size / i;
                if (singleSize < SIZE_LIMIT) {
                    singleFile.sliceNum = i;
                    singleFile.sliceSize = (int) singleSize;
                    singleFile.isEqualSzie = false;

                    System.out.println("不等分隔大小=> " + size + " 分隔数=>" + i + " 大小=> " + printSize(singleSize));
                    return singleFile;
                }
            }
        } else {
            for (int i = coreNum; ; i++) {
                if (size % i == 0) {
                    long singleSize = size / i;
                    if (singleSize < SIZE_LIMIT) {
                        singleFile.sliceNum = i;
                        singleFile.sliceSize = (int) singleSize;
                        singleFile.isEqualSzie = true;
                        System.out.println("均匀大小=> " + size + " 分隔数=>" + i + " 大小=> " + printSize(singleSize));

                        return singleFile;
                    }
                }
            }
        }

    }

    /**
     * 获取分片文件列表
     *
     * @param src
     * @return
     */
    public static List<File> getFileList(String src) {
        return getMergeFileList(src).stream().map(f -> f.file).collect(Collectors.toList());
    }

    private static List<MergeFile> getMergeFileList(String src) {
        File file = new File(src);
        if (!file.isDirectory()) {
            throw new RuntimeException("源路径必须是目录");
        }

        List<MergeFile> mgList = Arrays.stream(Objects.requireNonNull(file.listFiles()))
                .filter(f -> f.getName().contains(SPLIT_FLAG))
                .map(f -> {
                    String name = f.getName();
                    String[] split = name.split(SPLIT_FLAG);
                    if (split.length < 2) {
                        throw new RuntimeException("错误文件名称: " + name);
                    }
                    String idStr = split[1];

                    MergeFile mergeFile = new MergeFile();
                    mergeFile.file = f;
                    mergeFile.id = Integer.parseInt(idStr);

                    return mergeFile;
                }).collect(Collectors.toList());

        return mgList;
    }

    private static void doFileSplit(List<Slice> slices, File srcfile, String dest) {
        final CountDownLatch cdl = new CountDownLatch(slices.size());
        try {
            for (Slice slice : slices) {
                executor.submit(() -> doSingleFileSplit(slice, srcfile, dest, cdl));
            }
            cdl.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static Boolean doSingleFileSplit(Slice slice, File srcFile, String dest, CountDownLatch cdl) {
        String baseDestName = dest + srcFile.getName() + SPLIT_FLAG + slice.id;
        File file = new File(baseDestName);
        if (file.exists()) {
            //System.out.println("删除源文件=>"+baseDestName);
            file.delete();
        }
        System.out.println(Thread.currentThread().getName() + " => 处理 => " + baseDestName);
        int bufSize = 1024;
        try (RandomAccessFile rafR = new RandomAccessFile(srcFile, "r");
             FileOutputStream fos = new FileOutputStream(file)
        ) {
            byte[] buf = new byte[bufSize];
            int n;
            for (long fseek = slice.startIdx; ; ) {
                int remainSize = (int) (slice.endIdx - fseek) + 1;
                rafR.seek(fseek);
                if (remainSize > bufSize) {//还可读取整块
                    n = rafR.read(buf, 0, bufSize);
                } else {//读取剩余
                    n = rafR.read(buf, 0, remainSize);
                }
                fos.write(buf, 0, n);

                fseek = fseek + n;
                if (fseek + 1 > slice.endIdx) {
                    break;
                }
            }

//            for (long fseek = slice.startIdx; fseek <= slice.endIdx; fseek++) {
//                rafR.seek(fseek);
//                fos.write(rafR.read());
//            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(Thread.currentThread().getName() + " => 处理失败 => " + baseDestName);
            return false;
        } finally {
            cdl.countDown();
        }
        System.out.println(Thread.currentThread().getName() + " => 处理完成 => " + baseDestName);

        return true;
    }

    private static List<Slice> getSlice(long size) {
        return doComputeSlice(sugSize(size));
    }

    public static void showGetSlice(long size) {
        getSlice(size).forEach(System.out::println);
    }

    private static List<Slice> doComputeSlice(SingleFile singleFile) {
        List<Slice> slices = new LinkedList<>();
        if (singleFile.isEqualSzie) {
            int sliceNum = singleFile.sliceNum;
            for (int i = 0; i < sliceNum; i++) {
                Slice slice = new Slice();
                slice.id = i;
                slice.startIdx = ((long) i * singleFile.sliceSize);
                if (i + 1 == sliceNum) {
                    slice.endIdx = (slice.startIdx + singleFile.sliceSize);
                } else {
                    slice.endIdx = (slice.startIdx + singleFile.sliceSize - 1);
                }
                slices.add(slice);
            }
        } else {
            long srcSize = singleFile.srcSize;
            int fSize = singleFile.sliceSize;
            int fNum = singleFile.sliceNum;
            long fseek = 0;
            for (int i = 0; i < fNum; i++) {
                Slice slice = new Slice();
                slice.id = i;
                slice.startIdx = fseek;
                if (i + 1 == fNum) {
                    slice.endIdx = srcSize;
                } else {
                    slice.endIdx = fseek + fSize;
                    fseek = fseek + fSize + 1;
                }
                slices.add(slice);
            }
        }

        return slices;
    }

    private static String printSize(long size) {
        if (size > GB) {
            float res = size/(GB*1.0f);
            return decimalFormat.format(res)+"GB";
        } else if (size > MB) {
            float res = size/(MB*1.0f);
            return decimalFormat.format(res)+"MB";
        } else if (size > KB) {
            float res = size/(KB*1.0f);
            return decimalFormat.format(res)+"KB";
        } else {
            return (size + "B");
        }
    }

    private static SingleFile sugSize(long size) {
        return suggestionSize(size, null);
    }

    private static boolean isPrime(long number) {
        if (number <= 1) {
            return false;
        }

        // 从2开始，逐个检查能否整除
        for (long i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                // 能整除，不是质数
                return false;
            }
        }
        // 无法整除，是质数
        return true;
    }

    private static boolean isLimitSplit(long size) {
        int limitNum = (int) (size / SIZE_LIMIT);
        int num;
        for (int i = CORE_SIZE; ; i++) {
            if (size % i == 0) {
                long singleSize = size / i;
                if (singleSize < SIZE_LIMIT) {
                    num = i;
                    break;
                }
            }
        }

        return num > limitNum;
    }

    private static class SingleFile {
        boolean isEqualSzie;
        /**
         * 分隔文件大小
         */
        int sliceSize;

        /**
         * 分隔文件数量
         */
        int sliceNum;

        long srcSize;
    }

    private static class Slice {
        int id;
        long startIdx;
        long endIdx;

        @Override
        public String toString() {
            return "Slice{" +
                    "id=" + id +
                    ", startIdx=" + startIdx +
                    ", endIdx=" + endIdx +
                    '}';
        }
    }

    private static class MergeFile {
        int id;
        File file;

        @Override
        public String toString() {
            return "MergeFile{" +
                    "id=" + id +
                    '}';
        }
    }

}
