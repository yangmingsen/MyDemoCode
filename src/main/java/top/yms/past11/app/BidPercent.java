package top.yms.past11.app;

import java.math.BigDecimal;

public class BidPercent {
    public static void main1(String[] args) {
        BigDecimal startPrice = new BigDecimal("9456.345");
        Integer bidPercent = 7;
        double y = (1d - (bidPercent/100d));
        BigDecimal yy = new BigDecimal(y);
        BigDecimal multiply = startPrice.multiply(yy).setScale(3,  BigDecimal.ROUND_HALF_UP);

        System.out.println(multiply.toString());

    }

    public static void main(String[] args) {
        BigDecimal startPrice = new BigDecimal("9456.345");
        BigDecimal bidPercent = new BigDecimal("5");
        BigDecimal one = new BigDecimal("1");
        BigDecimal per100 = new BigDecimal("100");

        BigDecimal divideRes = bidPercent.divide(per100,2,2);
        BigDecimal subtractRes = one.subtract(divideRes);
        BigDecimal multiplyRes = startPrice.multiply(subtractRes);
        System.out.println(multiplyRes);


        //BigDecimal multiply = startPrice.multiply(one.subtract(fd.divide(new BigDecimal("100"))));
    }

    public static void main3(String[] args) {
        BigDecimal bidPercent = new BigDecimal("5");
        BigDecimal per100 = new BigDecimal("100");
        System.out.println(bidPercent.divide(per100,2,2));

    }

}
