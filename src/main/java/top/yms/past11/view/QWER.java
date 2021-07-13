package top.yms.past11.view;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QWER {

    static int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n;
    }

    public static void main(String[] args) {
        System.out.println(tableSizeFor(30));

    }
    public static void main1(String[] args) {
        HashMap<String, Object> map = new HashMap<String, Object>() {{
            put("ids", new ArrayList<Integer>() {{
                add(1);
                add(2);
                add(3);
            }});
            put("blog_id", 1);
        }};

        map.put("goodsIs", new Integer(234234));
        map.put("acid", "234234234jlsdf");


        String json = JSON.toJSONString(map);
        System.out.println(json);


        for (Map.Entry<String, Object> entity : map.entrySet()) {
            System.out.println(entity.getKey());
            System.out.println(entity.getValue());
        }

    }
}
