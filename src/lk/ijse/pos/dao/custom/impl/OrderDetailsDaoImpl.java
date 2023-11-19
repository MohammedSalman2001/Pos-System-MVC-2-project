package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.CrudDao;
import lk.ijse.pos.dao.CrudUtil;
import lk.ijse.pos.dao.custom.OrderDao;
import lk.ijse.pos.dao.custom.OrderDetailsDao;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.entity.OrderDetails;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDetailsDaoImpl implements OrderDetailsDao {
    @Override
    public boolean save(OrderDetails d) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into `Order Details` values(?,?,?,?)",
                d.getCode(),d.getOrderId(),d.getUnitePrice(),d.getQty());
    }

    @Override
    public boolean update(OrderDetails i) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update item set qtyOnHand=(qtyOnHand-?) where code=?",
                i.getQty(),i.getCode());
    }

    @Override
    public boolean delete(String searchText) throws SQLException, ClassNotFoundException {
        return false;
    }
}
