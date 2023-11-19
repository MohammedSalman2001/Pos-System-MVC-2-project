package lk.ijse.pos.bo.custom;

import lk.ijse.pos.dto.CustomDto;


import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryBo {
    public ArrayList<CustomDto> loadItemDetails(String id) throws SQLException, ClassNotFoundException;
}
