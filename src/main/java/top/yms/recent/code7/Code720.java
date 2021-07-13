package top.yms.recent.code7;

import java.applet.Applet;
import java.awt.*;

public class Code720 extends Applet {

    public static void main(String[] args) {
        System.exit(0);
        Runtime.getRuntime().exit(0);
        System.out.println("1 + 2 == "+fun());
    }

    public static int fun() {
        int m;
        int n;
        m = 1;
        n = 2;
        int r = m + n;
        return r;
    }

    public void paint(Graphics g) {
        g.drawString("1 + 2 == "+fun(), 20, 20);
    }
}
