package lk.ijse.pos.bo.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.pos.bo.custom.OrderBo;
import lk.ijse.pos.dao.DaoFactory;
import lk.ijse.pos.dao.DaoType;
import lk.ijse.pos.dao.custom.OrderDao;
import lk.ijse.pos.dao.custom.OrderDetailsDao;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.dto.CustomerDto;
import lk.ijse.pos.dto.ItemDto;
import lk.ijse.pos.dto.OrderDetailsDto;
import lk.ijse.pos.dto.OrderDto;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.Order;
import lk.ijse.pos.entity.OrderDetails;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBoImpl implements OrderBo {
    OrderDao orderDao= DaoFactory.getInstance().getDao(DaoType.ORDER);
    OrderDetailsDao orderDetailsDao=DaoFactory.getInstance().getDao(DaoType.ORDERDETAILS);


    @Override
    public boolean saveOrder(OrderDto dto, ArrayList<OrderDetailsDto> details) throws SQLException {
        Connection con = null;


        try {
            con= DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isSave= orderDao.save(new Order(
                    dto.getOrderId(),dto.getDate(),dto.getCustomer(),dto.getTotalCost())
            );
            if(isSave){
                boolean isUpdate= manageQty(details);
                if(isUpdate){
                    con.commit();
                    new Alert(Alert.AlertType.INFORMATION,"Order saved!...");
                }else {
                    con.setAutoCommit(true);
                    con.rollback();
                    new Alert(Alert.AlertType.WARNING,"Try Again!...");
                }

            }else {
                con.setAutoCommit(true);
                con.rollback();
                new Alert(Alert.AlertType.WARNING,"Try Again").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            con.setAutoCommit(true);
        }
        return false;

    }

    private boolean manageQty(ArrayList<OrderDetailsDto> details) throws SQLException, ClassNotFoundException {



            for (OrderDetailsDto d : details
            ) {

                boolean isOrderDetailsSaved= orderDetailsDao.save(new OrderDetails(
                        d.getCode(),d.getOrderId(),d.getUnitePrice(),d.getQty())
                );


                if(isOrderDetailsSaved){
                    boolean isQtyUpdate = update(d);
                    if (!isQtyUpdate) {
                        return false;

                    }

                }else {
                    return  false;
                }


            }



        return true;

    }
    private boolean update(OrderDetailsDto d) throws SQLException, ClassNotFoundException {
            return orderDetailsDao.update(new OrderDetails(d.getQty(),d.getCode()));


    }

    @Override
    public ResultSet setOrderId() throws SQLException, ClassNotFoundException, Exception {
        return orderDao.setOrderId();
    }

    @Override
    public ArrayList<CustomerDto> setCustomerDetails(String id) throws SQLException, ClassNotFoundException {
        ArrayList<Customer> arrayList = orderDao.setCustomerDetails(id);
        ArrayList<CustomerDto>dtos=new ArrayList<>();
        for (Customer c: arrayList){
            dtos.add(new CustomerDto(
                    c.getName(),c.getAddress(),c.getSalary()
            ));
        }
        return dtos;

    }

    @Override
    public ArrayList<ItemDto> setItemDetails(String id) throws SQLException, ClassNotFoundException {
        ArrayList<Item> arrayList = orderDao.setItemDetails(id);
        ArrayList<ItemDto>dtos=new ArrayList<>();
        for (Item c: arrayList){
            dtos.add(new ItemDto(
                  c.getDescription(),c.getUnitePrice(),c.getQtyOnHand()
            ));
        }
        return dtos;

    }

    @Override
    public ResultSet loadItemCode() throws SQLException, ClassNotFoundException {
        return orderDao.loadItemCode();
    }

    @Override
    public ResultSet loadCustomerId() throws SQLException, ClassNotFoundException {
        return orderDao.loadCustomerId();
    }

    @Override
    public ResultSet checkQty(String code) throws SQLException, ClassNotFoundException {
        return orderDao.checkQty(code);
    }

    @Override
    public ArrayList<OrderDto> loadOrderTable() throws SQLException, ClassNotFoundException {
        ArrayList<Order> arrayList = orderDao.loadOrderTable();
        ArrayList<OrderDto> dtos=new ArrayList<>();
        for (Order o:arrayList){
            dtos.add(new OrderDto(
                    o.getOrderId(),o.getDate(),o.getCustomer(),o.getTotalCost()
            ));
        }
        return dtos;
    }
}
