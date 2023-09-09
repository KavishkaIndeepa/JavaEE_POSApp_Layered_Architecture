package pos.dao;

import pos.dao.custom.impl.CustomerDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    public DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        if(daoFactory==null){
            daoFactory=new DAOFactory();
        }
        return daoFactory;
    }
    public enum DoType{
        Customer
    }
    public SuperDAO getDo(DAOFactory.DoType doType){
        switch (doType){
            case Customer:
                return (SuperDAO) new CustomerDAOImpl();
        }
        return null;
    }
}
