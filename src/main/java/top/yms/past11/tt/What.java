package top.yms.past11.tt;

public class What {
    interface A {
        void say(String hello);
    }

    interface B{
        void say(String hello);
    }

    static class C implements A,B {
        public void say(String hello) {
            System.out.println("hello world "+hello);
        }
    }

    public static void main(String[] args) {
        C c = new C();
        A a  = c;
        a.say("what");

        B b = c;
        b.say("waht b");
    }

}
