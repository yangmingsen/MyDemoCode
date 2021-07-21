package top.yms.recent.c202101_07.code4;

import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class Code407 {
    public static void main(String[] args) throws Exception{
        Properties properties = new Properties();
        // 使用ClassLoader加载properties配置文件生成对应的输入流
        InputStream in = Code407.class.getClassLoader().getResourceAsStream("resource.properties");
        // 使用properties对象加载输入流
        properties.load(in);

        Set<String> strings = properties.stringPropertyNames();
        for(String str : strings) {
            System.out.println(properties.getProperty(str));
        }

    }
}
