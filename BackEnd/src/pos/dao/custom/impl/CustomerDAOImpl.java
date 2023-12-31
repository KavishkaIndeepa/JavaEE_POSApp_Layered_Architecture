package pos.dao.custom.impl;

import pos.dao.custom.CustomerDAO;
import pos.db.DbConnection;
import pos.entity.Customer;
import pos.util.CrudUtil;


import java.sql.*;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean save(Customer obj) {
        try( Connection connection = DbConnection.getDbConnection().getConnection()) {
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO customer VALUES (?,?,?)");
            pstm.setString(1,obj.getId());
            pstm.setString(2,obj.getName());
            pstm.setString(3,obj.getAddress());
            int rowsAffected = pstm.executeUpdate();
            return rowsAffected > 0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ResultSet getAll() throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getDbConnection().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM customer");
        return resultSet;
    }

    @Override
    public boolean update(Customer obj) throws SQLException, ClassNotFoundException {
        String sql="UPDATE  customer SET name=?,address=? WHERE id=?";
        return CrudUtil.execute(sql,obj.getName(),obj.getAddress(),obj.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql="DELETE FROM customer WHERE id=?";
        return CrudUtil.execute(sql,id);
    }
}
