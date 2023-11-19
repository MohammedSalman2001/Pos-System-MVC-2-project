package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDao;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDao extends CrudDao<Order,String> {

    public ResultSet setOrderId() throws SQLException, ClassNotFoundException, Exception;
    public ArrayList<Customer> setCustomerDetails(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<Item> setItemDetails(String id) throws SQLException, ClassNotFoundException;
    public ResultSet loadItemCode() throws SQLException, ClassNotFoundException;
    public ResultSet loadCustomerId() throws SQLException, ClassNotFoundException;
    public ResultSet checkQty(String code) throws SQLException, ClassNotFoundException;
    public ArrayList<Order>loadOrderTable() throws SQLException, ClassNotFoundException;

}
