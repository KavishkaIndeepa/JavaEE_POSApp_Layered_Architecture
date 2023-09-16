package pos.dao.custom;

import pos.dao.CrudDAO;
import pos.entity.Item;

import java.sql.SQLException;

public interface ItemDAO extends CrudDAO<Item> {
    boolean updateQty(double qty,String id) throws SQLException, ClassNotFoundException;
}
