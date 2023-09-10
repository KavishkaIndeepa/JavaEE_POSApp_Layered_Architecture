package pos.entity;

import java.math.BigDecimal;

public class PurchaseOrder {
    String oid;
    String code;
    int qty;
    double unitPrice;

    public String getOid() {
        return oid;
    }

    public String getCode() {
        return code;
    }

    public int getQty() {
        return qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public PurchaseOrder() {
    }

    public PurchaseOrder(String oid, String code, int qty, double unitPrice) {
        this.oid = oid;
        this.code = code;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }
}
