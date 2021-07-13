package top.yms.past11.test;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

public class Code3 {

    static long KB = 1024;
    static long MB = 1024*1024;
    static long GB = 1024*1024;

    public static void main(String[] args) {
        long startTime=System.currentTimeMillis();   //获取开始时间
        start();
        long endTime=System.currentTimeMillis(); //获取结束时间

        System.out.println("程序运行时间： "+(double)((endTime-startTime)/1000.0)+"s");
    }

    public static void start() {

        File files = new File("D:\\");
        File[] listFiles = files.listFiles();

        double totalSize = 0.0d; //MB

        for (int i = 0; i < listFiles.length; i++) {

            if (pathSkip(listFiles[i])) {
                double sizeD = getMaxPaths(listFiles[i]) / MB;
                String str = null;
                if (sizeD>0.0d) {
                    str = new Double(sizeD).toString().substring(0,6);
                } else {
                    str = new Double(sizeD).toString();
                }
                System.out.println(listFiles[i].getName()+":"+str+"MB");
                totalSize += sizeD;
            } else {
                double singleSize = listFiles[i].length() /(MB*1.0);
                String str = null;
                if (singleSize>0.0d) {
                    str = new Double(singleSize).toString().substring(0,6);
                } else {
                    str = new Double(singleSize).toString();
                }
                System.out.println(listFiles[i].getName()+":"+str+"MB");
                totalSize+= singleSize;
            }
        }


        if (totalSize > 1024.0d) {
            System.out.println("\n\ntotalSize = "+totalSize/1024+"GB");
        } else {
            System.out.println("\n\ntotalSize = "+totalSize+"MB");
        }


    }

    public static boolean pathSkip(File f) {
        if(f.isDirectory() && !f.getName().startsWith("$") && !f.getName().equals("System Volume Information")) return true;
        return false;
    }

    public static double getMaxPaths(File f) {
        File[] files = f.listFiles();
        double size = 0.0d;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                size += getMaxPaths(files[i]);
            } else {
                size += files[i].length();
            }
        }
        return size;
    }

    public static double getMaxPaths2(File f) {
        double totalSize = 0.0d;
        Queue<File> queue = new LinkedList();
        queue.add(f);

        while (!queue.isEmpty()) {
            File tf = queue.poll();
            if (tf.isDirectory()) {
                File[] files = tf.listFiles();
                for(File tf2 : files) {
                    queue.add(tf2);
                }
            }
            totalSize += tf.length();
        }

        return totalSize;
    }

}
