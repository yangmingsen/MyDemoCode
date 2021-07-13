package top.yms.recent.code1;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Code12 {
    public static void main(String[] args) {



        BigDecimal bigDecimal2 = new BigDecimal("0.00030012300000000000000000000000000000000000000000000000234000000023400000");
        bigDecimal2=bigDecimal2.add(new BigDecimal("1.00"));
        System.out.println(bigDecimal2.toString());
        BigDecimal bigDecimal1 = new BigDecimal("0.0003001230000000000000000000000000000000000000000000000023400000002340000000000000");
        bigDecimal1=bigDecimal1.add(new BigDecimal("1.000000"));
        System.out.println(bigDecimal1.toString());

        int x = bigDecimal2.compareTo(bigDecimal1);
        System.out.println(x);





    }
}
