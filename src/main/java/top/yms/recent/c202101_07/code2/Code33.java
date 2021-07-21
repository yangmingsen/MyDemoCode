package top.yms.recent.c202101_07.code2;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Code33 {

    static LinkedHashMap<String, String> get() {
        LinkedHashMap<String, String> llmap = new LinkedHashMap<>(10,0.75f,true);

        llmap.put("22x34","23423sdf");
        llmap.put("2a34","23423sdf");
        llmap.put("a2s34","23423sdf");

        return llmap;
    }

    public static void main(String[] args) {
        LinkedHashMap<String, String> linkedHashMap = get();
        linkedHashMap.put("2a34","yangmingsen");
        Set<Map.Entry<String, String>> entries = linkedHashMap.entrySet();

        for(Map.Entry<String, String> entry : entries) {
            System.out.println(entry.getKey()+"===>"+entry.getValue());
        }


    }

}
