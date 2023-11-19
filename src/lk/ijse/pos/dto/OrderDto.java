package lk.ijse.pos.dto;

import lk.ijse.pos.entity.OrderDetails;

import java.util.ArrayList;

public class OrderDto {
    private String OrderId;
    private String date;
    private String customer;
    private double totalCost;
    private ArrayList<OrderDetails> itemDetails;

    public OrderDto() {
    }

    public OrderDto(String orderId, String date, String customer, double totalCost) {
        OrderId = orderId;
        this.date = date;
        this.customer = customer;
        this.totalCost = totalCost;
    }

    public OrderDto(String orderId, String date, String customer, double totalCost, ArrayList<OrderDetails> itemDetails) {
        OrderId = orderId;
        this.date = date;
        this.customer = customer;
        this.totalCost = totalCost;
        this.itemDetails = itemDetails;
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

    public ArrayList<OrderDetails> getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(ArrayList<OrderDetails> itemDetails) {
        this.itemDetails = itemDetails;
    }
}
