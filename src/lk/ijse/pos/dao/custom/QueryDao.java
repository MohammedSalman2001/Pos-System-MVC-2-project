package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDao;
import lk.ijse.pos.dao.SuperDao;
import lk.ijse.pos.entity.Custom;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDao extends SuperDao {

    public ArrayList<Custom>loadItemDetails(String id) throws SQLException, ClassNotFoundException;
}

