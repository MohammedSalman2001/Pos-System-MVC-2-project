package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDao;
import lk.ijse.pos.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDao extends CrudDao<Item,String> {
    ArrayList<Item>searchItem(String searchText) throws SQLException, ClassNotFoundException;
}
