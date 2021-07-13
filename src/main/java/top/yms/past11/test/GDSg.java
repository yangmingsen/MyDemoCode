package top.yms.past11.test;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

class Message {
    private Integer type;
    private Object data;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Message{" +
                "type=" + type +
                ", data=" + data +
                '}';
    }
}

class Bid {
    private BigDecimal bid;
    private String bidTime;

    public BigDecimal getBid() {
        return bid;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    public String getBidTime() {
        return bidTime;
    }

    public void setBidTime(String bidTime) {
        this.bidTime = bidTime;
    }

    @Override
    public String toString() {
        return "Bid{" +
                "bid=" + bid +
                ", bidTime='" + bidTime + '\'' +
                '}';
    }
}

class res {
    private BigDecimal minPrice;
    private List<He> list;

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public List<He> getList() {
        return list;
    }

    public void setList(List<He> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "res{" +
                "minPrice=" + minPrice +
                ", list=" + list +
                '}';
    }
}

class He {
    private String supplierName;
    private Integer rank;
    private List<Bid> bids;

    @Override
    public String toString() {
        return "He{" +
                "supplierName='" + supplierName + '\'' +
                ", rank=" + rank +
                ", bids=" + bids +
                '}';
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }
}

public class GDSg {



    public static void main(String[] args) {
        List<Bid> bids = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Bid bid = new Bid();
            bid.setBid(new BigDecimal("1.2"+i));
            bid.setBidTime("14:3"+i);

            bids.add(bid);
        }

        List<He> hes = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            He he = new He();
            he.setBids(bids);
            he.setRank(i);
            he.setSupplierName("大米"+i);

            hes.add(he);
        }

        res rs = new res();
        rs.setList(hes);
        rs.setMinPrice(new BigDecimal("0.011"));

        Message message = new Message();
        message.setData(rs);
        message.setType(300);

        Gson gson = new Gson();
        String s1 = gson.toJson(message);
        System.out.println(s1);

        String s = JSON.toJSONString(message);
        System.out.println(s);


    }


}
