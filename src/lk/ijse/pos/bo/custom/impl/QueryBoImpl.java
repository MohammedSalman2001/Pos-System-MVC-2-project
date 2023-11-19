package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.QueryBo;
import lk.ijse.pos.dao.DaoFactory;
import lk.ijse.pos.dao.DaoType;
import lk.ijse.pos.dao.custom.QueryDao;
import lk.ijse.pos.dto.CustomDto;

import lk.ijse.pos.entity.Custom;

import java.sql.SQLException;
import java.util.ArrayList;

public class QueryBoImpl implements QueryBo {
    QueryDao queryDao= DaoFactory.getInstance().getDao(DaoType.QUERY);

    public ArrayList<CustomDto> loadItemDetails(String id) throws SQLException, ClassNotFoundException {
        ArrayList<Custom> arrayList = queryDao.loadItemDetails(id);
        ArrayList<CustomDto>dtos=new ArrayList<>();
        for (Custom c:arrayList){
            dtos.add(new CustomDto(
                    c.getCode(),
                    c.getUnitePrice(),
                    c.getQty()
            ));
        }
        return dtos;

    }
}
