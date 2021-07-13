package top.yms.past11.tt;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class OPgf {
    public static void main2(String[] args) {
        BigDecimal b1 = new BigDecimal("4.10");
        BigDecimal b2 = new BigDecimal("4.100");

        double v = b1.doubleValue();
        Double d1 = v;
        System.out.println(v);
        double v1 = b2.doubleValue();
        Double d2 = v1;

        System.out.println(hash(d1)+" "+hash(d2));

        System.out.println(v1);
        System.out.println("----------------");


        HashMap<Double, String> map = new HashMap<>();
        map.put(d1,"1");
        map.put(d2,"1");
        System.out.println(b1.compareTo(b2));
        for( Map.Entry<Double, String> entry : map.entrySet()) {
            System.out.println(entry.getKey()+" value="+entry.getValue());
        }

    }

    static int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }


    public static int compare(Double d1, Double d2) {
        return d1.compareTo(d2);
    }


    public static BigDecimal computeSubPercent(double firstPrice, double subPercent) {
        double computePriceRes = firstPrice * (1D - subPercent/100D);
        return new BigDecimal(computePriceRes).setScale(20,  BigDecimal.ROUND_HALF_UP);
    }

    public static void main(String[] args) {

        System.out.println(computeSubPercent(2.345d, 23.33d)+"  "+computeSubPercent(2.345d, 23.34d));


    }
}
