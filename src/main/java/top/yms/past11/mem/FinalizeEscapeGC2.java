package top.yms.past11.mem;

public class FinalizeEscapeGC2 {

    public static void main(String[] args) {
        FinalizeEscapeGC2 finalizeEscapeGC = new FinalizeEscapeGC2();
        finalizeEscapeGC = null;

        if (finalizeEscapeGC != null) {
            System.out.println("I'm Alive");
        } else {
            System.out.println("I'm Dead");
        }
    }
}
