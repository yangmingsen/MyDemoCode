package top.yms.recent.code8;

//方法静态分派演示
public class Code809 {

    static abstract class Human {
    }
    static class Man extends Human {
    }
    static class Woman extends Human {
    }
    public void sayHello(Human guy) {
        System.out.println("hello,guy!");
    }
    public void sayHello(Man guy) {
        System.out.println("hello,gentleman!");
    }
    public void sayHello(Woman guy) {
        System.out.println("hello,lady!");
    }
    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();

        Code809 sr = new Code809();
        sr.sayHello(man);
        sr.sayHello(woman);
    }

}
