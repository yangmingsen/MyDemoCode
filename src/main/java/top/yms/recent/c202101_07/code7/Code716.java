package top.yms.recent.c202101_07.code7;

import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public class Code716 {
    public static void main(String[] args) {

        String var0 = System.getProperty("java.security.policy");
        System.out.println("vao="+var0);

        final String var1 = System.getProperty("java.class.path");
        System.out.println(var1);

        Properties properties = System.getProperties();

        Set<String> strings = properties.stringPropertyNames();

        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println(next+": "+properties.getProperty(next));
        }
    }
}
