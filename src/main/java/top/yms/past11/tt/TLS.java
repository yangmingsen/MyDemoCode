package top.yms.past11.tt;

import java.math.BigDecimal;

public class TLS {
    public static void main(String [] args) {
        BigDecimal bigDecimal = new BigDecimal("0.1");
        BigDecimal bigDecimal2 = new BigDecimal("0.2");
        System.out.println(bigDecimal.compareTo(bigDecimal2));

        BigDecimal bigDecimal11 = new BigDecimal("0.1");
        BigDecimal bigDecimal22 = new BigDecimal("0.1");
        System.out.println(bigDecimal11.compareTo(bigDecimal22));

        BigDecimal bigDecimal111 = new BigDecimal("0.2");
        BigDecimal bigDecimal222 = new BigDecimal("0.1");
        System.out.println(bigDecimal111.compareTo(bigDecimal222));
    }
}
