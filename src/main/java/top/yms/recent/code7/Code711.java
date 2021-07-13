package top.yms.recent.code7;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Code711<T> {

    interface Aaa {

    }

    interface Ccc extends Aaa,Bbb {

    }

    interface Bbb extends Aaa {

    }




    static class Aa<T> {

        private T a ;

        public T getA() {
            return a;
        }

        public void setA(T a) {
            this.a = a;
        }
    }



    static class Bb<T> extends Aa<T> {
        private T a;

        public T getA() {
            return a;
        }

        public void setA(T a) {
            this.a = a;
        }
    }


    static class Cc<T> extends Bb<T> {
        private T a ;

        public T getA() {
            return a;
        }

        public void setA(T a) {
            this.a = a;
        }


    }

    public void doSome2(List<? extends Bb> list) {

    }



    public <T extends Aa> T doSome(T t) {
        //do Some
        return t;
    }




    public static void main(String[] args) {
        ArrayList<Cc> strings = new ArrayList<>();
        Code711 code711 = new Code711();
        List<Integer> list = new ArrayList<>();
        code711.doSome2(list);
        List<Aa> list22 = new ArrayList<>();
        List<? super Bb> lits2 = list22;

    }

    public static void main1(String[] args) throws Exception {
        Aa aa = new Aa();
        Class<? extends Aa> aClass = aa.getClass();
        String name = aClass.getName();
        System.out.println(aa.getClass().getName()+"---"+name);

        Integer integer = new Integer(2);
        Class<Integer> integerClass = int.class;
        Class<? extends Integer> aClass1 = integer.getClass();
        Class<?> aClass2 = Class.forName(aClass1.getName());

    }





}
