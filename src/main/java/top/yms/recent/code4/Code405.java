package top.yms.recent.code4;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class Code405 {

    static class Logger {
        public void error(String arg) {
            System.out.println(arg);
        }
    }

    public static void main(String[] args) {

        Logger logger = new Logger();

        //调用api
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("http://api.esrcloud.com:8041/api/nsspr/list");
        // 响应模型
        CloseableHttpResponse response = null;
        try {
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
