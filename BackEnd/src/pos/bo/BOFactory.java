package pos.bo;

import pos.bo.custom.impl.CustomerBOImpl;

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
        Customer
    }
    public SuperBO getBoType(BOFactory.BoType boType){
        switch (boType){
            case Customer:
                return (SuperBO) new CustomerBOImpl();
        }
        return null;
    }
}
