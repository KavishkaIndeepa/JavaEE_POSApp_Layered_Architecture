package pos.db;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DbConnection {
    private  static DbConnection dbConnection;
    BasicDataSource dataSource;
    private DbConnection() throws SQLException {
        dataSource=new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/samadhi");
        dataSource.setPassword("1234");
        dataSource.setUsername("root");
        dataSource.setMaxTotal(500);
        dataSource.setInitialSize(500);
        Connection connection=dataSource.getConnection();
    }

    public static DbConnection getDbConnection() throws SQLException {
        if(dbConnection==null){
            dbConnection=new DbConnection();
        }
        return dbConnection;
    }
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
