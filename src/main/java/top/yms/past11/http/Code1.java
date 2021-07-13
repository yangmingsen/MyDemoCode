package top.yms.past11.http;

import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.common.HttpHeader;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import org.apache.http.Header;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


class GetIt implements Runnable {

    private String url = "http://192.168.17.222:8046/";
    private List<String> list = new ArrayList<>();

    @Override
    public void run() {
        String url2 = "http://192.168.17.222:8046/pc/seat/list?code";
        System.out.println("start thread:"+Thread.currentThread().getName());
        long count = 0;

        while (true) {
            try {
                HttpClientUtil.get(HttpConfig.custom().url(url2));
            } catch (HttpProcessException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName()+" request total="+count);
            count++;

        }

    }
}

class GetIt2 implements Runnable{
    @Override
    public void run() {
        String url = "http://192.168.17.222:8046/pc/reservation/getReservationStaff?selectDate=&code=";
        System.out.println("start thread:"+Thread.currentThread().getName());
        long count = 0;

        while (true) {
            try {
                String s = HttpClientUtil.get(HttpConfig.custom().url(url));
            } catch (HttpProcessException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName()+" request total="+count);
            count++;

        }
    }
}

class GetIt3 implements Runnable{
    @Override
    public void run() {
        String url = "http://192.168.17.222:8046/pc/seat/updateSeat";
        System.out.println("start thread:"+Thread.currentThread().getName());
        long count = 0;

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("codes", "192.168.17.222:8046");
        map.put("typeCode", "%select * from %");
        map.put("departmentCode", "%select * from 1%");


        //插件式配置Header（各种header信息、自定义header）
        Header[] headers = HttpHeader.custom()
                .userAgent("javacl")
                .other("customer", "自定义")
                .build();

        //插件式配置请求参数（网址、请求参数、编码、client）
        HttpConfig config = HttpConfig.custom()
                .headers(headers)	//设置headers，不需要时则无需设置
                .url(url)	          //设置请求的url
                .map(map)	          //设置请求参数，没有则无需设置
                .encoding("utf-8") //设置请求和返回编码，默认就是Charset.defaultCharset()
                //.inenc("utf-8")  //设置请求编码，如果请求返回一直，不需要再单独设置
                //.inenc("utf-8")	//设置返回编码，如果请求返回一直，不需要再单独设置
                //.json("json字符串")                          //json方式请求的话，就不用设置map方法，当然二者可以共用。
                //.context(HttpCookies.custom().getContext()) //设置cookie，用于完成携带cookie的操作
                //.out(new FileOutputStream("保存地址"))       //下载的话，设置这个方法,否则不要设置
                //.files(new String[]{"d:/1.txt","d:/2.txt"}) //上传的话，传递文件路径，一般还需map配置，设置服务器保存路径
                ;


        while (true) {
            try {
                String result2 = HttpClientUtil.post(config);    //post请求
                System.out.println(result2);
            } catch (HttpProcessException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName()+" request total="+count);
            count++;

        }
    }
}
public class Code1 {

    private static int num = 1000;

    private static ExecutorService pool = Executors.newFixedThreadPool(num);

    public static void main(String[] args) throws Exception {
        //最简单的使用：

        for (int i = 0; i < num/2; i++) {
            pool.execute(new GetIt3());
        }
        for (int i = 0; i < num/2; i++) {
            pool.execute(new GetIt2());
        }

    }

}
