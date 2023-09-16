package pos.dao;

import pos.dao.custom.impl.CustomerDAOImpl;
import pos.dao.custom.impl.ItemDAOImpl;
import pos.dao.custom.impl.OrderDAOImpl;
import pos.dao.custom.impl.PurchaseOrderDAOImpl;

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
        Customer,Item,Order,PurchaseOrder
    }
    public SuperDAO getDo(DAOFactory.DoType doType){
        switch (doType){
            case Customer:
                return (SuperDAO) new CustomerDAOImpl();
            case Item:
                return (SuperDAO) new ItemDAOImpl();
            case Order:
                return (SuperDAO) new OrderDAOImpl();
            case PurchaseOrder:
                return (SuperDAO) new PurchaseOrderDAOImpl();
        }
        return null;
    }
}
