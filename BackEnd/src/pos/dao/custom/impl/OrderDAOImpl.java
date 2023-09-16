package pos.dao.custom.impl;

import pos.dao.custom.OrderDAO;
import pos.entity.Order;
import pos.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public boolean save(Order obj) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO orders VALUES (?,?,?)",obj.getOrderId(),obj.getOrderDate(),obj.getCusId());
    }

    @Override
    public ResultSet getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(Order obj) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
