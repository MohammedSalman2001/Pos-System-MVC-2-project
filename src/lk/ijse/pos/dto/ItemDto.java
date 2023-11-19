package lk.ijse.pos.dto;

public class ItemDto {
    private String code;
    private String description;
    private double unitePrice;
    private int qtyOnHand;

    public ItemDto() {
    }

    public ItemDto(String description, double unitePrice, int qtyOnHand) {
        this.description = description;
        this.unitePrice = unitePrice;
        this.qtyOnHand = qtyOnHand;
    }

    public ItemDto(String code, String description, double unitePrice, int qtyOnHand) {
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
