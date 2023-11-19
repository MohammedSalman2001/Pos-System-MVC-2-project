package lk.ijse.pos.dto;

public class OrderDetailsDto {
    private String code;
    private String OrderId;
    private double unitePrice;
    private int qty;

    public OrderDetailsDto() {
    }

    public OrderDetailsDto( int qty,String code) {
        this.qty = qty;
        this.code = code;

    }

    public OrderDetailsDto(String code, String orderId, double unitePrice, int qty) {
        this.code = code;
        OrderId = orderId;
        this.unitePrice = unitePrice;
        this.qty = qty;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
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
