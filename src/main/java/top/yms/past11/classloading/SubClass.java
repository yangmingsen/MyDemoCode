package top.yms.past11.classloading;

public class SubClass extends SuperClass {
    static {
        System.out.println("SubClass init!");
    }
}