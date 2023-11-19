package lk.ijse.pos.tm;

import javafx.scene.control.Button;

public class CartTm {
    private String code;
    private String description;
    private double unitePrice;
    private int qty;
    private double total;
    private Button btn;


    public CartTm() {
    }

    public CartTm(String code, String description, double unitePrice, int qty, double total, Button btn) {
        this.code = code;
        this.description = description;
        this.unitePrice = unitePrice;
        this.qty = qty;
        this.total = total;
        this.btn = btn;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
