package pos.bo.custom;

import pos.bo.SuperBO;
import pos.dto.ItemDTO;

import java.sql.SQLException;
import java.util.List;

public interface ItemBO extends SuperBO {
    boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException;
    List<ItemDTO> getAllItem()throws SQLException, ClassNotFoundException;
    boolean updateItem(ItemDTO itemDTO)throws SQLException, ClassNotFoundException;
    boolean deleteItem(String id) throws SQLException, ClassNotFoundException;
}
