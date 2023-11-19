package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.CrudUtil;
import lk.ijse.pos.dao.custom.ItemDao;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.entity.Item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDaoImpl implements ItemDao {
    @Override
    public boolean save(Item i) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into Item values(?,?,?,?)", i.getCode(), i.getDescription(), i.getUnitePrice(), i.getQtyOnHand());
    }

    @Override
    public boolean update(Item i) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update Item set description=?,unitePrice=?,qtyOnHand=? where code=?",
                i.getDescription(), i.getUnitePrice(), i.getQtyOnHand(), i.getCode());

    }

    @Override
    public boolean delete(String searchText) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Item WHERE code=?", searchText);
    }

    @Override
    public ArrayList<Item> searchItem(String searchText) throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT * FROM Item WHERE description LIKE? || code LIKE ?", searchText, searchText);
        ArrayList<Item> arrayList = new ArrayList<>();
        while (set.next()) {
            arrayList.add(new Item(
                    set.getString(1),
                    set.getString(2),
                    set.getDouble(3),
                    set.getInt(4)
            ));

        }
        return arrayList;
    }
}
