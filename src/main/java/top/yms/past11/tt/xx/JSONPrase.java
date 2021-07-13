package top.yms.past11.tt.xx;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JSONPrase {
    public static void main1(String[] args) {
        String jstr = "[{\"created_time\":1589378679000,\"code\":\"1\",\"name\":\"timwu@esrgear.com\",\"state\":0,\"type\":\"email\",\"value\":\"58476173\"},{\"created_time\":1589378759000,\"code\":\"2\",\"name\":\"annyang@esrgear.com\",\"state\":0,\"type\":\"email\",\"value\":\"58476173\"},{\"created_time\":1589378899000,\"code\":\"3\",\"name\":\"105737049\",\"state\":0,\"type\":\"removeDepartmentCode\",\"value\":\"办公室\"},{\"created_time\":1589384683000,\"code\":\"4\",\"name\":\"0\",\"state\":0,\"type\":\"seatType\",\"value\":\"灵活座位\"},{\"created_time\":1589384714000,\"code\":\"5\",\"name\":\"1\",\"state\":0,\"type\":\"seatType\",\"value\":\"固定座位\"},{\"created_time\":1589384739000,\"code\":\"6\",\"name\":\"2\",\"state\":0,\"type\":\"seatType\",\"value\":\"公共座位\"},{\"created_time\":1591970979000,\"code\":\"7\",\"name\":\"miao.wang@esrgear.com\",\"state\":0,\"type\":\"email\",\"value\":\"58476172\"}]";
        String jstr2 = "[{\"msg\":\"成功\",\"companyCode\":\"46883\",\"shopCode\":\"s0000016\",\"sysCode\":\"195547033522532352\",\"accountCode\":\"tm001\",\"params\":\"{\\\"accountCode\\\":\\\"tm001\\\",\\\"companyCode\\\":\\\"46883\\\",\\\"countryCode\\\":\\\"CN\\\",\\\"endTime\\\":null,\\\"id\\\":null,\\\"jsonData\\\":null,\\\"jsonStr\\\":null,\\\"optionalFields\\\":null,\\\"orderData\\\":null,\\\"orderId\\\":null,\\\"pageSize\\\":50,\\\"platform\\\":null,\\\"requestTime\\\":null,\\\"scopeTime\\\":0,\\\"shopCode\\\":\\\"s0000016\\\",\\\"startTime\\\":null,\\\"sysCode\\\":\\\"195547033522532352\\\",\\\"tableName\\\":null,\\\"type\\\":null}\",\"type\":\"com.galaplat.platformdocking.tb.service.sync.OrderCenterSyncData\",\"result\":\"{\\\"add\\\":0,\\\"endDate\\\":null,\\\"fail\\\":5,\\\"invalid\\\":0,\\\"msg\\\":null,\\\"startDate\\\":\\\"1\\\",\\\"status\\\":1,\\\"total\\\":5,\\\"update\\\":0,\\\"value\\\":null}\",\"countryCode\":\"CN\",\"startTime\":{\"$date\":1548242229060},\"_id\":{\"$oid\":\"5c484d389bcda3000144969e\"},\"_class\":\"com.galaplat.platformdocking.base.core.params.MongonLogParams\",\"id\":\"3a690c2e-d32f-4c70-b4b5-a25c4945c4aa\",\"endTime\":{\"$date\":1548242232841},\"status\":1},{\"msg\":\"成功\",\"companyCode\":\"46883\",\"shopCode\":\"s0000016\",\"sysCode\":\"195547033522532352\",\"accountCode\":\"tm001\",\"params\":\"{\\\"accountCode\\\":\\\"tm001\\\",\\\"companyCode\\\":\\\"46883\\\",\\\"countryCode\\\":\\\"CN\\\",\\\"endTime\\\":null,\\\"id\\\":null,\\\"jsonData\\\":null,\\\"jsonStr\\\":null,\\\"optionalFields\\\":null,\\\"orderData\\\":null,\\\"orderId\\\":null,\\\"pageSize\\\":50,\\\"platform\\\":null,\\\"requestTime\\\":null,\\\"scopeTime\\\":0,\\\"shopCode\\\":\\\"s0000016\\\",\\\"startTime\\\":null,\\\"sysCode\\\":\\\"195547033522532352\\\",\\\"tableName\\\":null,\\\"type\\\":null}\",\"type\":\"com.galaplat.platformdocking.tb.service.sync.OrderCenterSyncData\",\"result\":\"{\\\"add\\\":0,\\\"endDate\\\":null,\\\"fail\\\":1,\\\"invalid\\\":0,\\\"msg\\\":null,\\\"startDate\\\":\\\"1\\\",\\\"status\\\":1,\\\"total\\\":1,\\\"update\\\":0,\\\"value\\\":null}\",\"countryCode\":\"CN\",\"startTime\":{\"$date\":1548249089519},\"_id\":{\"$oid\":\"5c4868029bcda3000144969f\"},\"_class\":\"com.galaplat.platformdocking.base.core.params.MongonLogParams\",\"id\":\"ed216d6a-0ef5-44a3-8fc1-197ccf83b256\",\"endTime\":{\"$date\":1548249090478},\"status\":1}]";
        JSONArray listJson = JSON.parseArray(jstr2);
        JSONArray listJsonA = JSON.parseArray(jstr);
        for (int i = 0; i < listJson.size(); i++) {
            JSONObject jsonObject = listJson.getJSONObject(i);
            Set<Map.Entry<String, Object>> entries = jsonObject.entrySet();
            for (Map.Entry<String, Object> en: entries) {
//                System.out.println(en.getKey()+" :"+en.getValue());
            }
        }

        Map<String, Object> itemMap = new HashMap<String, Object>(){{put("key", listJsonA);}};
        JSONObject itemJSONObj = JSONObject.parseObject(JSON.toJSONString(itemMap));

        listJson.add(itemJSONObj);

        System.out.println(listJson);

    }


    public static void ma2in1(String[] args) {
        String jstr1 = "[{\"created_time\":1589378679000,\"code\":\"1\",\"name\":\"timwu@esrgear.com\",\"state\":0,\"type\":\"email\",\"value\":\"58476173\"},{\"created_time\":1589378759000,\"code\":\"2\",\"name\":\"annyang@esrgear.com\",\"state\":0,\"type\":\"email\",\"value\":\"58476173\"},{\"created_time\":1589378899000,\"code\":\"3\",\"name\":\"105737049\",\"state\":0,\"type\":\"removeDepartmentCode\",\"value\":\"办公室\"},{\"created_time\":1589384683000,\"code\":\"4\",\"name\":\"0\",\"state\":0,\"type\":\"seatType\",\"value\":\"灵活座位\"}]";
        String jstr2 = "[{\"created_time\":1589378679000,\"code\":\"1\",\"name\":\"timw2u@esrgear.com\",\"state\":0,\"type\":\"email\",\"value\":\"58476173\"},{\"created_time\":1589378759000,\"code\":\"2\",\"name\":\"annyange@esrgear.com\",\"state\":0,\"type\":\"email\",\"value\":\"58476173\"},{\"created_time\":1589378899000,\"code\":\"3\",\"name\":\"105733ds7049\",\"state\":0,\"type\":\"removeDepartmentCode\",\"value\":\"办公室\"},{\"created_time\":1589384683000,\"code\":\"4\",\"name\":\"105737049\",\"state\":0,\"type\":\"seatType\",\"value\":\"灵活座位\"}]";

        JSONArray aJsonList = JSON.parseArray(jstr1);
        JSONArray bJsonList = JSON.parseArray(jstr2);

        Map<String, String> aValues = searchValues("name", aJsonList);
        Map<String, String> bValues = searchValues("name", bJsonList);

        System.out.println(doCheck(aValues, bValues));


    }

    public static boolean doCheck(Map<String, String> aValues, Map<String, String> bValues) {
        for(Map.Entry<String,String> aEntry : aValues.entrySet()) {
            final String aValue = aEntry.getKey();
            if (bValues.get(aValue) != null) {
                return true;
            }
        }
        return false;
    }

    private static Map<String, String> searchValues(final String searchKey, final JSONArray searchValue) {
        final int aJsonListObjLength = searchValue.size();
        final Map<String, String> values = new HashMap<>();
        for (int i = 0; i < aJsonListObjLength; i++) {
            final JSONObject jsonObject = searchValue.getJSONObject(i);
            final Set<Map.Entry<String, Object>> subEntries = jsonObject.entrySet();
            for (final Map.Entry<String, Object> subEntity : subEntries) {
                final String key = subEntity.getKey();
                if (searchKey.equals(key)) {
                    final String value = subEntity.getValue().toString();
                    if ("{".equals(value) || "[".equals(value)) continue;
                    values.put(value, searchKey);
                    break;
                }
            }
        }
        return values;
    }



    public static void main(String[] args) {
        String jstr = "{\"code\":\"id\",\"name\":\"name\",\"na23me\":\"namesdf\",}";
        JSONObject jsonObject = JSON.parseObject(jstr);
        Map<String, Object> innerMap = jsonObject.getInnerMap();
        for(Map.Entry<String, Object> entry : innerMap.entrySet()) {
            System.out.println(entry.getKey()+" "+entry.getValue());
        }

    }

}
