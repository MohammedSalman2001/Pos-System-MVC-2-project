package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.CrudUtil;
import lk.ijse.pos.dao.custom.QueryDao;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.entity.Custom;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDaoImpl implements QueryDao {
    @Override
    public ArrayList<Custom> loadItemDetails(String id) throws SQLException, ClassNotFoundException {
        ArrayList<Custom> arrayList=new ArrayList<>();
        ResultSet set = CrudUtil.execute("select o.orderId,d.itemCode,d.orderId,d.unitePrice,d.qty from `Order` o INNER JOIN `Order Details` d ON o.orderId=d.orderId AND o.orderId=?",id);
        while (set.next()){
            arrayList.add(new Custom(
                set.getString(2),
                    set.getDouble(4),
                    set.getInt(5)
            ));

        }
        return  arrayList;

    }
}
