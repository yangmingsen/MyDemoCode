package top.yms.past11.tt;

import java.math.BigDecimal;


class A {
    private Integer status;
    private String activityCode;
    private String creator;
    private String createdTime;
    private String simpleProductNo;
    private Integer supplierNum;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getActivityCode() {
        return activityCode;
    }

    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getSimpleProductNo() {
        return simpleProductNo;
    }

    public void setSimpleProductNo(String simpleProductNo) {
        this.simpleProductNo = simpleProductNo;
    }

    public Integer getSupplierNum() {
        return supplierNum;
    }

    public void setSupplierNum(Integer supplierNum) {
        this.supplierNum = supplierNum;
    }
}
public class LD {
    public static void main(String[] args) {
        BigDecimal b21 = new BigDecimal("4.500");
        BigDecimal b22 = new BigDecimal("4.500");
        System.out.println(b21.compareTo(b21));
    }
}
