package top.yms.custom;

public class AliExpendOther extends AliExpend{
    private String txStatus;

    public String getTxStatus() {
        return txStatus;
    }

    public void setTxStatus(String txStatus) {
        this.txStatus = txStatus;
    }

    @Override
    public String toString() {
        return "AliExpendOther{" + super.toString() +
                "txStatus='" + txStatus + '\'' +
                '}';
    }
}
