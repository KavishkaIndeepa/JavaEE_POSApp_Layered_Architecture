package pos.bo.custom.impl;

import pos.bo.custom.ItemBO;
import pos.dao.DAOFactory;
import pos.dao.custom.ItemDAO;
import pos.dto.ItemDTO;
import pos.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBOIml implements ItemBO {
    ItemDAO itemDAO= (ItemDAO) DAOFactory.getDaoFactory().getDo(DAOFactory.DoType.Item);
    @Override
    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new Item(dto.getCode(),dto.getDescription(),dto.getQtyOnHand(),dto.getUnitPrice()));
    }

    @Override
    public List<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
       List<ItemDTO> allItem=new ArrayList<>();
        ResultSet set = itemDAO.getAll();
        while (set.next()){
            ItemDTO dto=new ItemDTO();
            dto.setCode(set.getString("code"));
            dto.setDescription(set.getString("description"));
            dto.setQtyOnHand(set.getString("qtyOnHand"));
            dto.setUnitPrice(set.getString("unitPrice"));
            allItem.add(dto);
        }
        set.close();
        return allItem;
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(itemDTO.getCode(),itemDTO.getDescription(),itemDTO.getQtyOnHand(),itemDTO.getUnitPrice()));
    }

    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(code);
    }
}
