package top.yms.recent.c202204;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

public class Code20220506 {


    @Test
    public void testApp022() throws Exception {
        String cName = "top.yms.utils.ListNode";
        Class cl = Class.forName(cName);

        Method[] ml = cl.getDeclaredMethods();

        for (Method method : ml) {

            System.out.println(method.toString());
        }



    }

}
