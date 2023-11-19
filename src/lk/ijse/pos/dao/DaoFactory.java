package lk.ijse.pos.dao;

import lk.ijse.pos.dao.custom.impl.*;

public class DaoFactory {
    private static DaoFactory daoFactory;

    private DaoFactory(){}

    public static DaoFactory getInstance(){
        return daoFactory ==null? (daoFactory= new DaoFactory()):daoFactory;
    }

    public <T>T getDao(DaoType daoType){
         switch (daoType){
             case CUSTOMER:
               return (T) new CustomerDaoImpl();

             case ITEM:
                 return (T) new ItemDaoImpl();

             case ORDER:
                 return (T) new OrderDaoImpl();

             case ORDERDETAILS:
                 return (T) new OrderDetailsDaoImpl();

             case QUERY:
                 return (T) new QueryDaoImpl();
             default:
                 return null;



         }
    }


}
