package pos.bo;

import pos.bo.custom.impl.CustomerBOImpl;
import pos.bo.custom.impl.ItemBOIml;
import pos.bo.custom.impl.PurchaseOrderBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    public BOFactory() {
    }

    public static BOFactory getBoFactory() {
        if(boFactory==null){
            boFactory=new BOFactory();
        }
        return boFactory;
    }
    public enum BoType{
        Customer,Item,PurchaseOrder
    }
    public SuperBO getBoType(BOFactory.BoType boType){
        switch (boType){
            case Customer:
                return (SuperBO) new CustomerBOImpl();
            case Item:
                return (SuperBO) new ItemBOIml();
            case PurchaseOrder:
                return (SuperBO) new PurchaseOrderBOImpl();
        }
        return null;
    }
}
