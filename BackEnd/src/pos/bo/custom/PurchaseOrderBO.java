package pos.bo.custom;

import pos.dto.OrderDTO;
import pos.dto.PurchaseOrderDTO;

import java.sql.SQLException;
import java.util.List;

public interface PurchaseOrderBO {
    public boolean placeOder(OrderDTO ordDTO, List<PurchaseOrderDTO> purchaseOrderDTOS) throws SQLException, ClassNotFoundException;
}
