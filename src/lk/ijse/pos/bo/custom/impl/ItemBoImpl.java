package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.ItemBo;
import lk.ijse.pos.dao.DaoFactory;
import lk.ijse.pos.dao.DaoType;
import lk.ijse.pos.dao.custom.ItemDao;
import lk.ijse.pos.dto.ItemDto;
import lk.ijse.pos.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBoImpl implements ItemBo {

    ItemDao dao= DaoFactory.getInstance().getDao(DaoType.ITEM);
    @Override
    public boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        return dao.save(new Item(
                dto.getCode(),dto.getDescription(),dto.getUnitePrice(),dto.getQtyOnHand()
        ));
    }

    @Override
    public boolean updateItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        return dao.update(new Item(
                dto.getCode(),dto.getDescription(),dto.getUnitePrice(),dto.getQtyOnHand()
        ));
    }

    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        return dao.delete(id);
    }

    @Override
    public ArrayList<ItemDto> searchItem(String searchText) throws SQLException, ClassNotFoundException {
        ArrayList<Item> arrayList = dao.searchItem(searchText);
        ArrayList<ItemDto> dtos=new ArrayList<>();
        for (Item i:arrayList){
            dtos.add(new ItemDto(
                    i.getCode(),i.getDescription(),i.getUnitePrice(),i.getQtyOnHand()
            ));
        }
        return dtos;
    }
}
