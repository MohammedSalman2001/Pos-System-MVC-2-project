package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.CrudUtil;
import lk.ijse.pos.dao.custom.OrderDao;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean save(Order o) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO `Order` VALUES(?,?,?,?)",
                o.getOrderId(), o.getDate(), o.getTotalCost(), o.getCustomer());
    }

    @Override
    public boolean update(Order order) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String searchText) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ResultSet setOrderId() throws Exception {
//        " SELECT id FROM`Order` ORDER BY id DESC LIMIT 1"
        return CrudUtil.execute("select  orderId   from `Order` order by  orderId   desc limit 1");

    }

    @Override
    public ArrayList<Customer> setCustomerDetails(String id) throws SQLException, ClassNotFoundException {
        ArrayList<Customer> arrayList = new ArrayList<>();
        ResultSet set = CrudUtil.execute("SELECT * FROM Customer WHERE customerId=?", id);
        while (set.next()) {
            arrayList.add(new Customer(
                    set.getString(2),
                    set.getString(3),
                    set.getDouble(4)
            ));
        }
        return arrayList;
    }

    @Override
    public ArrayList<Item> setItemDetails(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT * FROM Item WHERE code=?", id);
        ArrayList<Item> arrayList = new ArrayList<>();
        while (set.next()) {
            arrayList.add(new Item(
                    set.getString(2),
                    set.getDouble(3),
                    set.getInt(4)

            ));

        }
        return arrayList;
    }

    @Override
    public ResultSet loadItemCode() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT code FROM Item");
        return set;
    }

    @Override
    public ResultSet loadCustomerId() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT customerId FROM Customer");
        return set;
    }

    @Override
    public ResultSet checkQty(String code) throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT qtyOnHand FROM Item WHERE code=?",code);
        return set;
    }

    @Override
    public ArrayList<Order> loadOrderTable() throws SQLException, ClassNotFoundException {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String simpleDate = dateFormat.format(date); // 2023-01-10
        ArrayList<Order> arrayList = new ArrayList<>();
        ResultSet set =CrudUtil.execute("select * from `order`");
        while (set.next()) {
            arrayList.add(new Order(
                    set.getString(1),
                    simpleDate,
                    set.getString(4),
                    set.getDouble(3)
            ));
        }
        return arrayList;

    }
}
