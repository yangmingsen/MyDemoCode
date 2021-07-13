package top.yms.past11.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestReflect {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class<?> computerClass = Class.forName("top.yms.past11.reflect.Computer");
        System.out.println(computerClass.getClasses());
        System.out.println(computerClass.getClassLoader());
        Method getName = computerClass.getMethod("getName");
        Constructor<?> constructor = computerClass.getConstructor(String.class, double.class);
        Object dell = constructor.newInstance("dell-computer", 1934.11D);

        Object invoke = getName.invoke(dell);
        System.out.println(invoke);
    }
}
