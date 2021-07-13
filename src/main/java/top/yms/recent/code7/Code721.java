package top.yms.recent.code7;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Code721 {

    public static void main(String[] args) {
        List<String> perNo = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            perNo.add("1141"+i);
        }

        Object [] arr = {"sdfds",1,3424234l, 23423.34d};
        System.out.println(Arrays.toString(arr));

        JSONArray jsonArray = new JSONArray();
        for(int i=0; i<perNo.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("PersonNo", perNo.get(i));
            jsonArray.add(jsonObject);
        }

        System.out.println(jsonArray.toJSONString());


    }

}
