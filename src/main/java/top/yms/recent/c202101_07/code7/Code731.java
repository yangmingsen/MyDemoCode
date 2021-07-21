package top.yms.recent.c202101_07.code7;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Code731 {

    static class Logger {
        public void error(String arg) {
            System.out.print(arg);
        }
    }

    static class RunTask implements Runnable  {
        @Override
        public void run() {
            Logger logger = new Logger();

            //调用api
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet httpGet = new HttpGet("http://localhost:8840/goods/code?code=234234");
            // 响应模型


            CloseableHttpResponse response = null;
            try {

                for(int i=0; i<3000; i++) {

                    // 由客户端执行(发送)Get请求
                    response = httpClient.execute(httpGet);
                    // 从响应模型中获取响应实体
                    HttpEntity responseEntity = response.getEntity();
                    String resLine = response.getStatusLine().toString();
                    logger.error("响应状态为:" + response.getStatusLine().toString());
                    if (resLine.indexOf("200") > 0) System.out.println("Yes");
                    if (responseEntity != null) {
                        logger.error("响应内容长度为:" + responseEntity.getContentLength());
                        logger.error("响应内容为:" + EntityUtils.toString(responseEntity));
                    }
                    System.out.println(i);

                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    // 释放资源
                    if (httpClient != null) {
                        httpClient.close();
                    }
                    if (response != null) {
                        response.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i=0; i<1; i++) {
            executorService.execute(new RunTask());
        }
    }

}
