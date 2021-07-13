package top.yms.recent.code9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TheYTask {
    /**
     * Http get请求
     * @param httpUrl 连接
     * @return 响应数据
     */
    public static String doGet(String httpUrl) throws Exception{
        //链接
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        StringBuffer result = new StringBuffer();

        try {
            //创建连接
            URL url = new URL(httpUrl);
            connection = (HttpURLConnection) url.openConnection();
            //设置请求方式
            connection.setRequestMethod("GET");
            //设置连接超时时间
            connection.setReadTimeout(15000);
            //开始连接
            connection.connect();
            //获取响应数据
            if (connection.getResponseCode() == 200) {
                //获取返回的数据
                is = connection.getInputStream();
                if (null != is) {
                    br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                    String temp = null;
                    while (null != (temp = br.readLine())) {
                        result.append(temp);
                    }
                }
            }
        } catch (IOException e) {
            throw new IOException(e);
        } finally {
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    throw new IOException(e);
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    throw new IOException(e);
                }
            }
            //关闭远程连接
            connection.disconnect();
        }
        return result.toString();
    }

    private static final String PATTERN4 = "yyyy-MM-dd HH:mm:ss";
    private static String match(String pattern) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String timeStampStr = simpleDateFormat.format(date);
        return timeStampStr;
    }
    /***
     * get yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getDateTime() {
        return match(PATTERN4);
    }

    public static String parseException(Exception e) {
        if (e == null) return "null";
        StringBuffer tmpStr = new StringBuffer();
        String exceptionName = e.getClass().getName();
        String cause = e.getMessage();
        tmpStr.append(exceptionName).append(" : ").append(cause).append("\n");

        for (StackTraceElement stackTrace : e.getStackTrace()) {
            tmpStr.append("\t∧ ").append(stackTrace.toString()).append("\n");
        }

        return tmpStr.toString();
    }

    public static void main(String[] args) throws Exception {

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);

        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                String url = "http://api.esrcloud.com:8041/api/nsspr/updateClient";
                try {
                    System.out.println(getDateTime()+" 开始执行：GET "+url);
                    String s = doGet(url);
                    System.out.println(getDateTime()+" 执行完成："+s);
                } catch (Exception e) {
                    System.out.println(parseException(e));
                }
            }
        },0,5, TimeUnit.MINUTES);

    }

}
