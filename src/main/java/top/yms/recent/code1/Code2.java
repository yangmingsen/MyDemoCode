package top.yms.recent.code1;

import org.apache.commons.lang3.StringUtils;
import top.yms.past11.test.Ha;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static java.lang.Math.*;

public class Code2 implements Cloneable {

    String checkData(Object data) {
        if (data != null) {
            return data.toString();
        } else {
            return "";
        }
    }




    private StringBuffer name;
    private Integer age;




    @Override
    public String toString() {
        return "Code2{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Code2 code2 = new Code2();

        BigDecimal bigDecimal = new BigDecimal("234.34");
        Integer a = new Integer("234");
        Long ll = null;
        Map map = new HashMap();
        map.put("sdf","sdf");
        System.out.println(code2.checkData(a));
        System.out.println(code2.checkData(bigDecimal));
        System.out.println(code2.checkData(ll));
        System.out.println(code2.checkData(map));



    }
}
