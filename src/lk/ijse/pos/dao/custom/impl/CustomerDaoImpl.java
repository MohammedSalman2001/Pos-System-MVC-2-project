package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.CrudUtil;
import lk.ijse.pos.dao.custom.CustomerDao;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.entity.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean save(Customer c1) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO CUSTOMER VALUES (?,?,?,?)",
                c1.getCustomerId(),c1.getName(),c1.getAddress(),c1.getSalary());
    }

    @Override
    public boolean update(Customer c1) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Customer SET customerName=?,customerAddress=?,customerSalary=? WHERE customerId=?",
                c1.getName(),c1.getAddress(),c1.getSalary(),c1.getCustomerId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Customer WHERE customerId=?",id);
    }

    @Override
    public ArrayList<Customer> searchCustomer(String searchText) throws SQLException, ClassNotFoundException {
        ArrayList<Customer> arrayList=new ArrayList<>();
        ResultSet set = CrudUtil.execute("SELECT * FROM Customer WHERE customerName LIKE ? || customerAddress LIKE ?",searchText,searchText);
        while (set.next()){
            arrayList.add(new Customer(
                    set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    set.getDouble(4)
            ));
        }
        return arrayList;
    }
}
