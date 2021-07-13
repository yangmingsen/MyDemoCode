package top.yms.recent.code7;

import top.yms.recent.NationDO;

import java.lang.reflect.Field;
import java.text.NumberFormat;
import java.util.ArrayList;

public class Code713 {
    public static void main(String[] args) {
        ObjectAnalyzer objAnalyzer = new ObjectAnalyzer();
        ArrayList<Integer> al = new ArrayList<>();
        for(int i=0;i<6;i++) {
            al.add(i*i);
        }
        System.out.println(objAnalyzer.toString(al));


        MyNation yms = new MyNation("yms", 18);
        System.out.println(objAnalyzer.toString(yms));

    }
}
