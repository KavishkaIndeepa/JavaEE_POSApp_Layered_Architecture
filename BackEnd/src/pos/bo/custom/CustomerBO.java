package pos.bo.custom;

import pos.bo.SuperBO;
import pos.dto.CustomerDTo;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO {
    public boolean saveCustomer(CustomerDTo cusDto) throws SQLException, ClassNotFoundException;
    public List<CustomerDTo> getAllCustomer() throws SQLException, ClassNotFoundException;
    public boolean updateCustomer(CustomerDTo cusDto) throws SQLException, ClassNotFoundException;
    boolean deleteCustomer(String id)throws SQLException, ClassNotFoundException;
}
