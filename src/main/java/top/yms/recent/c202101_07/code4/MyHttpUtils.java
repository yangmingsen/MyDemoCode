package top.yms.recent.c202101_07.code4;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;

public class MyHttpUtils {

    private final static String URL = "http://apitest.esrcloud.com/cmp/566972892500062208/api/demand/process/node";

    /**
     *
     * @param url 请求地址
     * @param header 请求头
     * @param jsonStr body
     * @return
     * @throws IOException
     */
    public static CloseableHttpResponse doPostByJson(String url, Map<String, String> header, String jsonStr) throws IOException {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建http POST请求
        HttpPost httpPost = new HttpPost(url);

        //构建header
        if (header != null) {
            for(Map.Entry<String, String> headerEntry : header.entrySet()) {
                httpPost.setHeader(headerEntry.getKey(), headerEntry.getValue());
            }
        }

        httpPost.setHeader("Content-Type", "application/json");

        // 组织数据
        StringEntity se = new StringEntity(jsonStr);
        //设置编码格式
        se.setContentEncoding("UTF-8");
        //设置数据类型
        se.setContentType("application/json");

        //对于POST请求,把请求体填充进HttpPost实体.
        httpPost.setEntity(se);

        return httpclient.execute(httpPost);
    }

    //   JSONObject jsonObject = new JSONObject();
    //        jsonObject.put("node","N4");
    //        jsonObject.put("orderId","173b8ef02d76xx7acfcd1028145d5a5a4b");
    //        jsonObject.put("startTime",new Date().getTime());
    //        jsonObject.put("sponsor","郑锐锐");
    //        jsonObject.put("sponsorCode","173b8ef02d76xx7acfcd1028145d5a5a4b");
    //
    //        JSONArray jsonArray = new JSONArray();
    //        for(int i=1; i<4; i++) {
    //            JSONObject jsonObject2 = new JSONObject();
    //            jsonObject2.put("approver","173b8ef02d76xx7acfcd1028145d5a5a4bx"+i);
    //            jsonObject2.put("approverCode","郑锐锐"+i);
    //            jsonObject2.put("language",+i);
    //
    //            jsonArray.add(jsonObject2);
    //        }
    //        jsonObject.put("data", jsonArray);

    public static void jTest() throws Exception {


        String jsonStr = "{\"data\":{\"message\":\"通过\",\"status\":1},\"node\":\"N3\",\"orderId\":\"1781bb1bdb0e39911f18e9641e3a0ab8\",\"sponsor\":\"杨铭森\",\"sponsorCode\":\"11413\",\"startTime\":1615372373804}";

        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("token", "tokencmp123");

        String s = doJsonRequest(URL, headerMap, jsonStr);

    }


    public static void main(String[] args) throws Exception {

       // jTest();

        String jsonStr = "{\"data\":{\"message\":\"通过\",\"status\":1},\"node\":\"N3\",\"orderId\":\"1781bb1bdb0e39911f18e9641e3a0ab8\",\"sponsor\":\"杨铭森\",\"sponsorCode\":\"11413\",\"startTime\":1615372373804}";

        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("token", "tokencmp123");
        doReq(headerMap, jsonStr);

    }

    private static void doReq( Map<String, String> headerMap, String jsonStr) throws Exception {
        try {
            System.out.println("execURL=【"+URL+"】==>"+"execHeader=【"+headerMap.toString()+"】===>"+"execReqBoy=【"+jsonStr+"】");
            String reqResult = doJsonRequest(URL, headerMap, jsonStr);
            System.out.println("reqResult==【"+reqResult.toString()+"】");
        } catch (Exception e) {
            System.out.println("ReqException=【"+e.getMessage()+"】");
            Thread.sleep(1*1000);
            doReq(headerMap,jsonStr);
        }
    }

    public static Map<String, Object> test1() {
        Map<String, Object> dataMap = new HashMap<>();

        dataMap.put("node","N4");
        dataMap.put("orderId","173b8ef02d76xx7acfcd1028145d5a5a4b");
        dataMap.put("startTime",new Date().getTime());
        dataMap.put("sponsor","郑锐锐");
        dataMap.put("sponsorCode","173b8ef02d76xx7acfcd1028145d5a5a4b");



        List<Map<String, Object>> listMap = new ArrayList<>();
        for(int i=1; i<4; i++) {
            Map<String, Object> tmpMap = new HashMap<>();
            tmpMap.put("approver","173b8ef02d76xx7acfcd1028145d5a5a4bx"+i);
            tmpMap.put("approverCode","郑锐锐"+i);
            tmpMap.put("language",+i);

            listMap.add(tmpMap);
        }
        dataMap.put("data", listMap);
        System.out.println(dataMap.toString());

        return dataMap;

    }


    public static Map<String, Object> test2() {
        Map<String, Object> dataMap = new HashMap<>();

        dataMap.put("node","N4");
        dataMap.put("orderId","173b8ef02d76xx7acfcd1028145d5a5a4b");
        dataMap.put("startTime",new Date().getTime());
        dataMap.put("sponsor","郑锐锐");
        dataMap.put("sponsorCode","173b8ef02d76xx7acfcd1028145d5a5a4b");

            Map<String, Object> tmpMap = new HashMap<>();
            tmpMap.put("approver","173b8ef02d76xx7acfcd1028145d5a5a4bx");
            tmpMap.put("approverCode","郑锐锐");
            tmpMap.put("language",1);
            dataMap.put("data",tmpMap);


        return dataMap;

    }



    /**
     * Map toJsonObjct
     * @param dataMap
     * @return
     */
    public static JSONObject parseToJsonObject(Map<String, Object> dataMap) {
        JSONObject jsonObject = new JSONObject();
        for(Map.Entry<String, Object> entry : dataMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if ("data".equals(key)) { //处理详细数据
                if(value instanceof HashMap) {
                    HashMap<String, Object> detailMap = (HashMap<String, Object>) value;
                    JSONObject deatilJsonObject = new JSONObject();
                    for(Map.Entry<String, Object> detailEntry : detailMap.entrySet()) {
                        String dKey = detailEntry.getKey();
                        Object dValue = detailEntry.getValue();

                        deatilJsonObject.put(dKey,dValue);
                    }
                    jsonObject.put(key, deatilJsonObject);
                } else if(value instanceof List) {
                    List<Map<String, Object>> listMap = (List<Map<String, Object>>)value;
                    JSONArray jsonArray = new JSONArray();
                    for(Map<String, Object> tmpMap : listMap) {
                        JSONObject deatilJsonObject = new JSONObject();
                        for(Map.Entry<String, Object> detailEntry : tmpMap.entrySet()) {
                            String dKey = detailEntry.getKey();
                            Object dValue = detailEntry.getValue();

                            deatilJsonObject.put(dKey,dValue);
                        }
                        jsonArray.add(deatilJsonObject);
                    }
                    jsonObject.put(key,jsonArray);
                }

            }  else {
                jsonObject.put(key, value);
            }
        }
        return jsonObject;
    }


    /**
     * 都Post请求
     * @param url
     * @param header
     * @param jsonStr
     * @return
     * @throws Exception
     */
    public static String doJsonRequest(String url, Map<String, String> header, String jsonStr) throws Exception {
        CloseableHttpResponse httpResponse = doPostByJson(url, header, jsonStr);

        String statusLine = httpResponse.getStatusLine().toString();

        HttpEntity responseEntity = httpResponse.getEntity();
        if (statusLine != null) {
            if (statusLine.indexOf("200") > 0) {

            } else {

            }
        }

        if (responseEntity != null) {
          //  System.out.println("响应内容长度为:" + responseEntity.getContentLength());
           // tmpStr.append("响应内容为:").append(EntityUtils.toString(responseEntity)).append("|");
            String resJson = EntityUtils.toString(responseEntity);
            return resJson;
        }

        return null;
    }


    


}
