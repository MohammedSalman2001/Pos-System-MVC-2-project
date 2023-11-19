package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.pos.bo.BoFactory;
import lk.ijse.pos.bo.BoType;
import lk.ijse.pos.bo.custom.CustomerBo;
import lk.ijse.pos.dao.DaoFactory;
import lk.ijse.pos.dao.DaoType;
import lk.ijse.pos.dao.custom.CustomerDao;
import lk.ijse.pos.dao.custom.impl.CustomerDaoImpl;
import lk.ijse.pos.db.DBConnection;

import lk.ijse.pos.dto.CustomerDto;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.tm.CustomerTM;


import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

public class CustomerFormController {
    public Label lblDate;
    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerAddress;
    public JFXTextField txtCustomerSalary;
    public JFXTextField txtSearch;
    public JFXTextField txtCustomerId;
    public TableView<CustomerTM> tblCustomer;

    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colSalary;
    public TableColumn colOption;
    public JFXButton btnSave;
    public AnchorPane root;

    public String searchText="";

    CustomerBo customerBo=BoFactory.getInstance().getBo(BoType.CUSTOMER);

    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        searchCustomer(searchText);
        setDate();


        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(null!=newValue){
                setValue(newValue);
            }
        });

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText=newValue;
            searchCustomer(searchText);
        });


    }

    private void setValue(CustomerTM tm) {
        txtCustomerId.setText(tm.getCustomerId());
        txtCustomerName.setText(tm.getName());
        txtCustomerAddress.setText(tm.getAddress());
        txtCustomerSalary.setText(String.valueOf(tm.getSalary()));
        btnSave.setText("update");
    }


    public void  setDate(){
        Timeline timeline=new Timeline(
                new KeyFrame(Duration.ZERO, e->{
                    DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss");
                    lblDate.setText(LocalDateTime.now().format(formatter));
                }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    public  void searchCustomer(String text){

        String searchText="%"+text+"%";
        ObservableList<CustomerTM> obList= FXCollections.observableArrayList();


        try {
            ArrayList<CustomerDto> arrayList = customerBo.searchCustomer(searchText);
            for (CustomerDto c1:arrayList){
                Button btn=new Button("delete");
                CustomerTM tm=new CustomerTM(
                      c1.getCustomerId(),
                        c1.getName(),
                        c1.getAddress(),
                       c1.getSalary(),
                        btn

                );
                obList.add(tm);

                btn.setOnAction(event -> {
                    Alert alert=new Alert(Alert.AlertType.WARNING,"Do you want delete Customer",ButtonType.YES,ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get()==ButtonType.YES){

                        try {

                            if (customerBo.deleteCustomer(tm.getCustomerId())){
                                new Alert(Alert.AlertType.INFORMATION,"Customer Delete").show();
                                searchCustomer(searchText);
                            }else {
                                new Alert(Alert.AlertType.WARNING,"Try Again").show();
                            }

                        }  catch (ClassNotFoundException |SQLException e) {
                            e.printStackTrace();
                        }

                    }

                });


            }
            tblCustomer.setItems(obList);

        } catch (ClassNotFoundException |SQLException e) {
            e.printStackTrace();
        }

    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        Customer c1= new Customer(txtCustomerId.getText(),txtCustomerName.getText(),txtCustomerAddress.getText(),Double.parseDouble(txtCustomerSalary.getText()));


        if (btnSave.getText().equalsIgnoreCase("Save")){
            try {
               /* String sql="INSERT INTO CUSTOMER VALUES (?,?,?,?)";
                PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
                statement.setString(1,c1.getCustomerId());
                statement.setString(2,c1.getName());
                statement.setString(3,c1.getAddress());
                statement.setDouble(4,c1.getSalary());*/
              boolean isSave=  customerBo.saveCustomer(new CustomerDto(txtCustomerId.getText(),txtCustomerName.getText(),txtCustomerAddress.getText(),Double.parseDouble(txtCustomerSalary.getText())));

                if( isSave){
                    Alert alert=new Alert(Alert.AlertType.INFORMATION,"Customer Saved");
                    alert.show();
                    clearAll();
                    searchCustomer(searchText);


                }else {
                    new Alert(Alert.AlertType.WARNING,"Try again").show();


                }

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e ) {
                e.printStackTrace();
            }

        }else {


            try {
             /*   String sql="UPDATE Customer SET customerName=?,customerAddress=?,customerSalary=? WHERE customerId=?";
                PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
                statement.setString(1,c1.getName());
                statement.setString(2,c1.getAddress());
                statement.setDouble(3,c1.getSalary());
                statement.setString(4,c1.getCustomerId());*/
               boolean isUpdate= customerBo.updateCustomer(new CustomerDto(txtCustomerId.getText(),txtCustomerName.getText(),txtCustomerAddress.getText(),Double.parseDouble(txtCustomerSalary.getText())));
                if(isUpdate ){
                    Alert alert=new Alert(Alert.AlertType.INFORMATION,"Customer Saved");
                    alert.show();
                    clearAll();
                    searchCustomer(searchText);


                }else {
                    new Alert(Alert.AlertType.WARNING,"Try again").show();


                }

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }






    }

    public void clearAll(){
        txtCustomerName.clear();
        txtCustomerId.clear();
        txtCustomerSalary.clear();
        txtCustomerAddress.clear();
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        btnSave.setText("Save");
        clearAll();
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage= (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/MenuForm.fxml"))));
    }
}
