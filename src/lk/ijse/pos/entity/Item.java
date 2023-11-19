package lk.ijse.pos.entity;

public class Item {
    private String code;
    private String description;
    private double unitePrice;
    private int qtyOnHand;


    public Item() {
    }

    public Item(String description, double unitePrice, int qtyOnHand) {
        this.description = description;
        this.unitePrice = unitePrice;
        this.qtyOnHand = qtyOnHand;
    }

    public Item(String code, String description, double unitePrice, int qtyOnHand) {
        this.code = code;
        this.description = description;
        this.unitePrice = unitePrice;
        this.qtyOnHand = qtyOnHand;
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

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }
}
