package top.yms.recent.c202101_07.code2;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Code33 {

    static LinkedHashMap<String, String> get() {
        LinkedHashMap<String, String> llmap = new LinkedHashMap<>(10,0.75f,true);

        llmap.put("A","1");
        llmap.put("B","2");
        llmap.get("A");
        llmap.put("C","3");
        llmap.get("B");

        return llmap;
    }

    public static void main(String[] args) {
        LinkedHashMap<String, String> linkedHashMap = get();
        linkedHashMap.put("D","4");
        linkedHashMap.get("B");
        Set<Map.Entry<String, String>> entries = linkedHashMap.entrySet();

        for(Map.Entry<String, String> entry : entries) {
            System.out.println(entry.getKey()+"===>"+entry.getValue());
        }


    }

}
