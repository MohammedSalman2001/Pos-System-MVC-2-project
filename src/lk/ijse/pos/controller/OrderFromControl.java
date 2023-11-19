package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.pos.bo.BoFactory;
import lk.ijse.pos.bo.BoType;
import lk.ijse.pos.bo.custom.OrderBo;
import lk.ijse.pos.dao.DaoFactory;
import lk.ijse.pos.dao.DaoType;
import lk.ijse.pos.dao.custom.OrderDao;
import lk.ijse.pos.dao.custom.OrderDetailsDao;
import lk.ijse.pos.dao.custom.impl.OrderDaoImpl;
import lk.ijse.pos.dao.custom.impl.OrderDetailsDaoImpl;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.dto.CustomerDto;
import lk.ijse.pos.dto.ItemDto;
import lk.ijse.pos.dto.OrderDetailsDto;
import lk.ijse.pos.dto.OrderDto;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.Order;
import lk.ijse.pos.entity.OrderDetails;
import lk.ijse.pos.tm.CartTm;



import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class OrderFromControl {
    public AnchorPane root;
    public JFXTextField txtOrderId;
    public TableView tblCart;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colUnitePrice;
    public TableColumn colQTY;
    public TableColumn colTotal;
    public TableColumn colOption;
    public JFXComboBox<String> cmbCustomerId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtSalary;
    public JFXComboBox<String> cmbItemCode;
    public JFXTextField txtDescription;
    public JFXTextField txtUnitePrice;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtQty;
    public Label lblTotal;
    public JFXTextField txtDate;

   OrderDao orderDao= DaoFactory.getInstance().getDao(DaoType.ORDER);
    OrderDetailsDao orderDetailsDao=DaoFactory.getInstance().getDao(DaoType.ORDERDETAILS);

    OrderBo orderBo= BoFactory.getInstance().getBo(BoType.ORDER);

    public void initialize(){
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitePrice.setCellValueFactory(new PropertyValueFactory<>("unitePrice"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
        loadCustomerId();
        loadItemCode();
        Date();
        setOrderId();

        cmbCustomerId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(null!=newValue){
                setCustomerDetails();
            }
        });

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(null!=newValue){
                setItemDetails();
            }
        });
    }

    public  void  setOrderId(){
        try{
            ResultSet resultSet = orderBo.setOrderId();
            boolean next = resultSet.next();
            if (next) {
                String userId = resultSet.getString(1);

                userId = userId.substring(1, userId.length());
                int intId = Integer.parseInt(userId);

                intId++;

                if (intId < 10) {
                    txtOrderId.setText("O00" + intId);

                } else if (intId < 100) {
                    txtOrderId.setText("O0" + intId);

                } else
                    txtOrderId.setText("O" + intId);

            } else {
                txtOrderId.setText("O001");
            }



        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void Date(){
        Date date= new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String simpleDate = dateFormat.format(date); // 2023-01-10
        txtDate.setText(simpleDate);

    }

    private void setCustomerDetails() {

        try {
            ArrayList<CustomerDto> arrayList = orderBo.setCustomerDetails(cmbCustomerId.getValue());
            for (CustomerDto c:arrayList){
                txtName.setText(c.getName());
                txtAddress.setText(c.getAddress());
                txtSalary.setText(String.valueOf(c.getSalary()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setItemDetails() {

        try {

            ArrayList<ItemDto> arrayList = orderBo.setItemDetails(cmbItemCode.getValue());
            for (ItemDto i:arrayList){
                txtDescription.setText(i.getDescription());
                txtUnitePrice.setText(String.valueOf(i.getUnitePrice()));
                txtQtyOnHand.setText(String.valueOf(i.getQtyOnHand()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadItemCode() {

        try {

            ResultSet set = orderBo.loadItemCode();
            ArrayList<String>arrayList=new ArrayList<>();
            while (set.next()){
                arrayList.add(set.getString(1));
            }
            ObservableList<String> obList=FXCollections.observableArrayList(arrayList);
            cmbItemCode.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadCustomerId() {

        try {
            ResultSet set = orderBo.loadCustomerId();
            ArrayList<String>arrayList=new ArrayList();

            while (set.next()){
                arrayList.add(set.getString(1));

            }
            ObservableList<String> obList= FXCollections.observableArrayList(arrayList);
            cmbCustomerId.setItems(obList);


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean checkQty(String code,int qty){

        try {
            ResultSet set = orderBo.checkQty(code);
            if(set.next()){
              int temQty=set.getInt(1);
              if(temQty>=qty){
                  return true;
              }else {
                  return false;
              }

            }else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;

    }
    ObservableList<CartTm> obList=FXCollections.observableArrayList();
    public void btnAddToCartOnAction(ActionEvent actionEvent) {


        if(!checkQty(cmbItemCode.getValue(),Integer.parseInt(txtQty.getText()))){
            new Alert(Alert.AlertType.WARNING,"Invalid entry").show();
            return;

        }

        double unitePrice=Double.parseDouble(txtUnitePrice.getText());
        int qty=Integer.parseInt(txtQty.getText());
        double total=unitePrice*qty;

        int row=allRedayExists(cmbItemCode.getValue());
        Button btn=new Button("Delete");

        if(row==-1){
            CartTm tm=new CartTm(cmbItemCode.getValue(),txtDescription.getText(),unitePrice,qty,total,btn);
            obList.add(tm);

            tblCart.setItems(obList);

        }else {
           int temQty= obList.get(row).getQty()+qty;
           double temTotal=unitePrice*temQty;

            if(!checkQty(cmbItemCode.getValue(),temQty)){
                new Alert(Alert.AlertType.WARNING,"Invalid entry").show();
                return;

            }


            obList.get(row).setQty(temQty);
           obList.get(row).setTotal(temTotal);
           tblCart.refresh();

        }
        calculateTotal();
        clear();


    }

    public int allRedayExists(String code) {
        for (int i = 0; i <obList.size() ; i++) {
            if(obList.get(i).getCode().equals(code)){
                return i;

            }

        }
        return -1;
    }



    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage= (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/MenuForm.fxml"))));
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) throws SQLException {
        if (obList.isEmpty()) return;
        ArrayList<OrderDetailsDto> details = new ArrayList<>();
        for (CartTm tm : obList) {
            details.add(new OrderDetailsDto(tm.getCode(), txtOrderId.getText(), tm.getUnitePrice(), tm.getQty()));

        }
       // Order o = new Order(txtOrderId.getText(), txtDate.getText(), cmbCustomerId.getValue(), Double.parseDouble(lblTotal.getText()), details);


        orderBo.saveOrder(new OrderDto(
                txtOrderId.getText(),
                txtDate.getText(),
                cmbCustomerId.getValue(),
                Double.parseDouble(lblTotal.getText())

        ),details);
        clearAll();

    }

     /*   Connection con = null;


        try {
            con= DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

           boolean isSave= orderDao.save(new Order(
                    txtOrderId.getText(),txtDate.getText(),cmbCustomerId.getValue(),Double.parseDouble(lblTotal.getText())
            ));
            if(isSave){
               boolean isUpdate= manageQty(details);
               if(isUpdate){
                   con.commit();
                   new Alert(Alert.AlertType.INFORMATION,"Order saved!...");
                   clearAll();
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

    }

    private boolean manageQty(ArrayList<OrderDetails> details) {


        try{
            for (OrderDetails d : details
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

        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();

        }


        return true;
    }
    private boolean update(OrderDetails d) {

        try {
           return orderDetailsDao.update(new OrderDetails(d.getQty(),d.getCode()));

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
            return false;
    }*/


    public void calculateTotal(){
        double total=0;
        for (int i = 0; i <obList.size() ; i++) {
            total+=obList.get(i).getTotal();

        }
        lblTotal.setText(String.valueOf(total));
    }

    public void clearAll(){
        setOrderId();
        obList.clear();
        calculateTotal();
        clear();
        cmbCustomerId.setValue(null);
        txtAddress.clear();
        txtName.clear();
        txtSalary.clear();

    }
    public void clear(){
        cmbItemCode.setValue(null);
        txtDescription.clear();
        txtQty.clear();
        txtQtyOnHand.clear();
        txtUnitePrice.clear();
    }
}
