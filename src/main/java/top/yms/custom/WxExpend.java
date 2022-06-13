package top.yms.custom;

import java.math.BigDecimal;
import java.util.Date;

public class WxExpend {
    private String orderId;
    private String counterparty; //对方
    private String title;
    private String paymentMethod;
    private BigDecimal amount;
    private String category;
    private Date txTime;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(String counterparty) {
        this.counterparty = counterparty;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getTxTime() {
        return txTime;
    }

    public void setTxTime(Date txTime) {
        this.txTime = txTime;
    }

    @Override
    public String toString() {
        return "AliExpend{" +
                "orderId='" + orderId + '\'' +
                ", counterparty='" + counterparty + '\'' +
                ", title='" + title + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", amount=" + amount +
                ", category='" + category + '\'' +
                ", txTime=" + txTime +
                '}';
    }
}
