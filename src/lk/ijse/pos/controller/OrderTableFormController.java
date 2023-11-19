package lk.ijse.pos.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.pos.bo.BoFactory;
import lk.ijse.pos.bo.BoType;
import lk.ijse.pos.bo.custom.OrderBo;
import lk.ijse.pos.dao.DaoFactory;
import lk.ijse.pos.dao.DaoType;
import lk.ijse.pos.dao.custom.OrderDao;
import lk.ijse.pos.dao.custom.impl.OrderDaoImpl;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.dto.OrderDto;
import lk.ijse.pos.entity.Order;
import lk.ijse.pos.tm.OrderTm;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class OrderTableFormController {
    public AnchorPane root;
    public TableView tblOrders;
    public TableColumn colOrderId;
    public TableColumn colDate;
    public TableColumn colCustomer;
    public TableColumn colTotalCost;
    public TableColumn ColOption;

   OrderBo orderBo= BoFactory.getInstance().getBo(BoType.ORDER);

    public void initialize(){
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colCustomer.setCellValueFactory(new PropertyValueFactory<>("customer"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTotalCost.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
        ColOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        loadTable();

    }

    public void loadTable(){
        ObservableList<OrderTm>obList= FXCollections.observableArrayList();



        try {
            ArrayList<OrderDto> arrayList = orderBo.loadOrderTable();
            for(OrderDto o:arrayList){
                Button btn=new Button("Item Details");
                OrderTm tm=new OrderTm(
                        o.getOrderId(),
                       o.getDate(),
                        o.getCustomer(),
                       o.getTotalCost(),btn);
                obList.add(tm);

                btn.setOnAction(event -> {
                    try {
                        FXMLLoader loader=new FXMLLoader(getClass().getResource("../view/ItemDetailForm.fxml"));
                        Parent parent=loader.load();
                        ItemDetailFormController controller =loader.getController();
                        controller.loadOrderDetails(tm.getOrderId());
                        Stage stage=new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

            }

            tblOrders.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnBackToHomeAction(ActionEvent actionEvent) throws IOException {
        Stage stage= (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/MenuForm.fxml"))));
    }
}
