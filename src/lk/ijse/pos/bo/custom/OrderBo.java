package lk.ijse.pos.bo.custom;

import lk.ijse.pos.dto.CustomerDto;
import lk.ijse.pos.dto.ItemDto;
import lk.ijse.pos.dto.OrderDetailsDto;
import lk.ijse.pos.dto.OrderDto;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderBo {
    public boolean saveOrder(OrderDto dto, ArrayList<OrderDetailsDto> details) throws SQLException;
    public ResultSet setOrderId() throws SQLException, ClassNotFoundException, Exception;
    public ArrayList<CustomerDto> setCustomerDetails(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<ItemDto> setItemDetails(String id) throws SQLException, ClassNotFoundException;
    public ResultSet loadItemCode() throws SQLException, ClassNotFoundException;
    public ResultSet loadCustomerId() throws SQLException, ClassNotFoundException;
    public ResultSet checkQty(String code) throws SQLException, ClassNotFoundException;
    public ArrayList<OrderDto>loadOrderTable() throws SQLException, ClassNotFoundException;
}
