package top.yms.recent.c202101_07.code7;

public class Code704 {

    private   <T> void doMapper(Class<T> tClass, String hello) {
        String s = tClass.toString();
        System.out.println(s);
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }


    public boolean equals2(Object obj) {
        return false;
    }

    public static void main(String[] args) {
        Code704 code704 = new Code704();
        code704.doMapper(Code704.class, " ");
    }


}
