package lk.ijse.pos.tm;

public class ItemDetailsTm {
    private String code;
    private double unitePrice;
    private int qty;
    private double total;

    public ItemDetailsTm(String code, double unitePrice, int qty, double total) {
        this.code = code;
        this.unitePrice = unitePrice;
        this.qty = qty;
        this.total = total;
    }

    public ItemDetailsTm() {
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
