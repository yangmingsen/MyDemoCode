package top.yms.recent.code1;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class Code5 {
    static class Person {
        private String name;
        private String bb;
        private String cc;
        private int age;
        private int num;
        private long l1;
        private long l2;


    }

    private static final Unsafe U;
    private static final long NAME_ADD;
    private static final long AGE_ADD;
    private static final long NUM;
    private static final long L1;
    private static final long L2;
    private static final long BB;
    private static final long CC;
    static {
        try {
//            U = sun.misc.Unsafe.getUnsafe();
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            U = (Unsafe) theUnsafe.get(null);
            Class<?> k = Person.class;
            NAME_ADD = U.objectFieldOffset(k.getDeclaredField("name"));
            AGE_ADD = U.objectFieldOffset(k.getDeclaredField("age"));
            NUM = U.objectFieldOffset(k.getDeclaredField("num"));
            L1 = U.objectFieldOffset(k.getDeclaredField("l1"));
            L2 = U.objectFieldOffset(k.getDeclaredField("l2"));
            BB = U.objectFieldOffset(k.getDeclaredField("bb"));
            CC = U.objectFieldOffset(k.getDeclaredField("cc"));
        }catch (Exception e) {
            throw new Error(e);
        }
    }

    public static void main(String[] args) {
        System.out.println("NAME="+NAME_ADD);
        System.out.println("AGE_ADD="+AGE_ADD);
        System.out.println("num="+NUM);
        System.out.println("L1="+L1);
        System.out.println("L2="+L2);
        System.out.println("BB="+BB);
        System.out.println("CC="+CC);
    }


}
