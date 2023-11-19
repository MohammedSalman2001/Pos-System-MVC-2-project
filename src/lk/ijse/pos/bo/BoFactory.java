package lk.ijse.pos.bo;

import lk.ijse.pos.bo.custom.impl.CustomerBoImpl;
import lk.ijse.pos.bo.custom.impl.ItemBoImpl;
import lk.ijse.pos.bo.custom.impl.OrderBoImpl;
import lk.ijse.pos.bo.custom.impl.QueryBoImpl;

public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory(){}

    public static BoFactory getInstance(){
        return boFactory==null?(boFactory=new BoFactory()):boFactory;
    }

    public <T>T getBo(BoType boType){
        switch (boType){
            case CUSTOMER:
               return (T) new CustomerBoImpl();

            case ITEM:
                return (T) new ItemBoImpl();

            case ORDER:
                return (T) new OrderBoImpl();

            case QUERY:
              return (T) new QueryBoImpl();

            default:
                return null;
        }

    }
}
