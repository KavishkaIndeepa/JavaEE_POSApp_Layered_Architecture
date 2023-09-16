package pos.bo.custom.impl;

import pos.bo.custom.PurchaseOrderBO;
import pos.dao.DAOFactory;
import pos.dao.custom.ItemDAO;
import pos.dao.custom.OrderDAO;
import pos.dao.custom.PurchaseOrderDAO;
import pos.db.DbConnection;
import pos.dto.OrderDTO;
import pos.dto.PurchaseOrderDTO;
import pos.entity.Order;
import pos.entity.PurchaseOrder;

import java.sql.SQLException;
import java.util.List;

public class PurchaseOrderBOImpl implements PurchaseOrderBO {

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDo(DAOFactory.DoType.Item);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDo(DAOFactory.DoType.Order);
    PurchaseOrderDAO detailDAO = (PurchaseOrderDAO) DAOFactory.getDaoFactory().getDo(DAOFactory.DoType.PurchaseOrder);

    @Override
    public boolean placeOder(OrderDTO ordDTO, List<PurchaseOrderDTO> purchaseOrderDTOS) throws SQLException, ClassNotFoundException {
        try {
            DbConnection.getDbConnection().getConnection().setAutoCommit(false);

            boolean isSave = orderDAO.save(new Order(ordDTO.getOrderId(), ordDTO.getOrderDate(), ordDTO.getCusId()));
            if (isSave) {
                for (PurchaseOrderDTO odt : purchaseOrderDTOS) {
                    boolean isSaveOD = detailDAO.save(new PurchaseOrder(odt.getOid(), odt.getCode(),
                            odt.getQty(), odt.getUnitPrice()));
                    if(isSaveOD){
                        boolean updateQty = itemDAO.updateQty(odt.getQty()-odt.getBuyQty(), odt.getCode());
                        if (updateQty){
                            DbConnection.getDbConnection().getConnection().commit();
                            return true;
                        }
                    }
                }
            }
            DbConnection.getDbConnection().getConnection().rollback();
            return false;
        }finally {
            DbConnection.getDbConnection().getConnection().setAutoCommit(true);
        }
    }
}
