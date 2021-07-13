package top.yms.past11.test;


import java.math.BigDecimal;

class Had {
    private String name;
    private BigDecimal bigDecimal;

    public Had(String name, BigDecimal bigDecimal) {
        this.name = name;
        this.bigDecimal = bigDecimal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBigDecimal() {
        return bigDecimal;
    }

    public void setBigDecimal(BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal;
    }

    @Override
    public String toString() {
        return "A{" +
                "name='" + name + '\'' +
                ", bigDecimal=" + bigDecimal +
                '}';
    }
}

public class Ha {
    public static void main(String[] args) {
        BigDecimal ai = new BigDecimal("4.324");
        BigDecimal a2 = new BigDecimal("34.324");
        System.out.println(ai.compareTo(a2));


    }
}
