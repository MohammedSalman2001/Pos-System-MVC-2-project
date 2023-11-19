package lk.ijse.pos.tm;

import javafx.scene.control.Button;

import java.util.Date;

public class OrderTm {
    private String OrderId;
    private String date;
    private String customer;
    private double totalCost;
    private Button btn;

    public OrderTm(String orderId, String date, String customer, double totalCost, Button btn) {
        OrderId = orderId;
        this.date = date;
        this.customer = customer;
        this.totalCost = totalCost;
        this.btn = btn;
    }

    public OrderTm() {
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
