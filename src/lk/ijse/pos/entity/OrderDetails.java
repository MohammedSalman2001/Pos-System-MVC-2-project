package lk.ijse.pos.entity;

import lk.ijse.pos.dto.OrderDetailsDto;

public class OrderDetails {
    private String code;
    private String OrderId;
    private double unitePrice;
    private int qty;


    public OrderDetails() {
    }

    public OrderDetails( int qty,String code) {
        this.qty = qty;
        this.code = code;

    }

    public OrderDetails(String code, String orderId, double unitePrice, int qty) {
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
