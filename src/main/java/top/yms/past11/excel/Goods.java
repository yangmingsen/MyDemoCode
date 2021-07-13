package top.yms.past11.excel;


public class Goods {
    private String goodsId;

    private String code;

    private String name;

    private Integer num;

    @Override
    public String toString() {
        return "Goods{" +
                "goodsId=" + goodsId +
                ", code=" + code +
                ", name='" + name + '\'' +
                ", num=" + num +
                '}';
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
