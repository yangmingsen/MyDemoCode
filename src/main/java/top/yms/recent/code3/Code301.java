package top.yms.recent.code3;

import top.yms.recent.code2.Code31;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Code301 {

    public static String fun1(List<String> list, Prace<String, String> prace) {
        StringBuilder tmpStr = new StringBuilder();
        for(String str : list) {
            tmpStr.append(prace.doOprate(str)).append(",");
        }
        return tmpStr.toString();
    }

    static List<String> getList(){
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            strings.add(i+"str");
        }
        return strings;
    }

    public void pritn() {
        System.out.println("hello world");
    }

    public static void main(String[] args) {

        String res = fun1(getList(),x ->
                x.replace("str","yangmingsen"));
        System.out.println(res);

        Code301 code301 = new Code301();
        Class<? extends Code301> aClass = code301.getClass();



    }




}
