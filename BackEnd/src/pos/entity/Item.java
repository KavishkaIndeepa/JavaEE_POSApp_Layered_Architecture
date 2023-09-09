package pos.entity;

import java.math.BigDecimal;

public class Item {
    String code;
    String description;
    int qtyOnHand;
    BigDecimal unitPrice;

    public Item(String code, String description, int qtyOnHand, BigDecimal unitPrice) {
        this.code = code;
        this.description = description;
        this.qtyOnHand = qtyOnHand;
        this.unitPrice = unitPrice;
    }

    public Item() {
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
}
