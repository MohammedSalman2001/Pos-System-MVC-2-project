package lk.ijse.pos.entity;

public class Custom {
    private String OrderId;
    private String code;
    private double unitePrice;
    private int qty;


    public Custom() {
    }

    public Custom(String code, double unitePrice, int qty) {
        this.code = code;
        this.unitePrice = unitePrice;
        this.qty = qty;
    }

    public Custom(String orderId, String code, double unitePrice, int qty) {
        OrderId = orderId;
        this.code = code;
        this.unitePrice = unitePrice;
        this.qty = qty;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getUnitePrice() {
        return unitePrice;
    }

    public void setUnitePrice(double unitePrice) {
        this.unitePrice = unitePrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
