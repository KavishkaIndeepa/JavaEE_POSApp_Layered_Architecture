package pos.dao.custom.impl;

import pos.dao.custom.PurchaseOrderDAO;
import pos.entity.PurchaseOrder;
import pos.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PurchaseOrderDAOImpl implements PurchaseOrderDAO {
    @Override
    public boolean save(PurchaseOrder obj) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO orderdetails VALUES (?,?,?,?)",obj.getOid(),obj.getCode(),
                obj.getQty(),obj.getUnitPrice());
    }

    @Override
    public ResultSet getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(PurchaseOrder obj) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
