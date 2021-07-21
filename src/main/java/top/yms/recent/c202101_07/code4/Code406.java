package top.yms.recent.c202101_07.code4;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Code406 {

    public static void main(String[] args) {
        String jsoStr = "{\"msg\":\"成功\",\"code\":\"1\",\"spenttime\":29265,\"level\":5}";

        String url = "http://localhost:8041/api/oa/put";
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("token", "123456x");
        try {
            CloseableHttpResponse httpResponse = MyHttpUtils.doPostByJson(url, headerMap, jsoStr);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = httpResponse.getEntity();

            System.out.println("响应状态为:" + httpResponse.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
