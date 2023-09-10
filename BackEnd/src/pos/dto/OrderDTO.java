package pos.dto;

import java.sql.Date;

public class OrderDTO {
    private String OrderId;
    private Date OrderDate;
    private String CusId;

    public OrderDTO(String orderId, Date orderDate, String cusId) {
        OrderId = orderId;
        OrderDate = orderDate;
        CusId = cusId;
    }

    public OrderDTO() {
    }

    public String getOrderId() {
        return OrderId;
    }

    public Date getOrderDate() {
        return OrderDate;
    }

    public String getCusId() {
        return CusId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public void setOrderDate(Date orderDate) {
        OrderDate = orderDate;
    }

    public void setCusId(String cusId) {
        CusId = cusId;
    }
}
