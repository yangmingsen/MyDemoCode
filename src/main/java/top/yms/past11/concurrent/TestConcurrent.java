package top.yms.past11.concurrent;

import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.common.HttpHeader;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import org.apache.http.Header;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestConcurrent {

    private static int count = 0;

    static class Run1 implements Runnable {
        long count = 10000;

        public void run() {
            String url = "http://localhost:8080/spring_app_demo_war_exploded/user/testRequestMapping?username=heihei";
            Map<String, Object> map = new HashMap<String, Object>();

            //插件式配置Header（各种header信息、自定义header）
            Header[] headers = HttpHeader.custom()
                    .userAgent("javacl")
                    .other("customer", "自定义")
                    .build();

            //插件式配置请求参数（网址、请求参数、编码、client）
            HttpConfig config = HttpConfig.custom()
                    .headers(headers)    //设置headers，不需要时则无需设置
                    .url(url)              //设置请求的url
                    .map(map)              //设置请求参数，没有则无需设置
                    .encoding("utf-8") //设置请求和返回编码，默认就是Charset.defaultCharset()
                    //.inenc("utf-8")  //设置请求编码，如果请求返回一直，不需要再单独设置
                    //.inenc("utf-8")	//设置返回编码，如果请求返回一直，不需要再单独设置
                    //.json("json字符串")                          //json方式请求的话，就不用设置map方法，当然二者可以共用。
                    //.context(HttpCookies.custom().getContext()) //设置cookie，用于完成携带cookie的操作
                    //.out(new FileOutputStream("保存地址"))       //下载的话，设置这个方法,否则不要设置
                    //.files(new String[]{"d:/1.txt","d:/2.txt"}) //上传的话，传递文件路径，一般还需map配置，设置服务器保存路径
                    ;

            while (count > 0) {
                try {
                    String result2 = HttpClientUtil.get(config);    //post请求
                } catch (HttpProcessException e) {
                    e.printStackTrace();
                }
                count--;
                //autoCount();
               System.out.println("线程"+Thread.currentThread().getId()+" -1 = "+count);
            }
            atomicInteger.getAndIncrement();
        }
    }
    private static final int num = 10;
    private static final AtomicInteger atomicInteger = new AtomicInteger(0);
    private static final ExecutorService pool = Executors.newFixedThreadPool(num);

    private static final Lock lock = new ReentrantLock();

    public static void autoCount() {
        lock.lock();
        try {
            count++;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        //最简单的使用：
        for (int i = 0; i < num; i++) {
            pool.execute(new Run1());
        }

        while (true) {
            if (atomicInteger.get() == 10) {
                final int res = count;
                System.out.println("count = "+res);
                break;
            }
        }

    }



}
