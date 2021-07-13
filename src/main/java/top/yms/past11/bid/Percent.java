package top.yms.past11.bid;

import java.math.BigDecimal;

public class Percent {

    private static BigDecimal computeSubPercent(double firstPrice, double subPercent) {
        double computePriceRes = firstPrice * (1d - subPercent/100d);
        return new BigDecimal(computePriceRes).setScale(3,  BigDecimal.ROUND_HALF_UP);
    }

    public static void main(String[] args) {
        double subPercent = 50.8899D;
        System.out.println(subPercent);

    }

}
