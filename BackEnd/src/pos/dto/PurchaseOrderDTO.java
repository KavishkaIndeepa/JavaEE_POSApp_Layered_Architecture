package pos.dto;

import java.math.BigDecimal;

public class PurchaseOrderDTO {

    String oid;
    String code;
    int qty;
    double unitPrice;
    double buyQty;

    public void setBuyQty(double buyQty) {
        this.buyQty = buyQty;
    }

    public double getBuyQty() {
        return buyQty;
    }

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

    public PurchaseOrderDTO() {
    }

    public PurchaseOrderDTO(String oid, String code, int qty, double unitPrice, double buyQty) {
        this.oid = oid;
        this.code = code;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.buyQty = buyQty;
    }


}
