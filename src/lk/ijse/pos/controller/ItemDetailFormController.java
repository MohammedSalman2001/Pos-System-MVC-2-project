package lk.ijse.pos.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.pos.bo.BoFactory;
import lk.ijse.pos.bo.BoType;
import lk.ijse.pos.bo.custom.QueryBo;
import lk.ijse.pos.dao.DaoFactory;
import lk.ijse.pos.dao.DaoType;
import lk.ijse.pos.dao.custom.QueryDao;
import lk.ijse.pos.dao.custom.impl.QueryDaoImpl;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.dto.CustomDto;
import lk.ijse.pos.entity.Custom;
import lk.ijse.pos.tm.ItemDetailsTm;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDetailFormController {
    public TableView tblItem;
    public TableColumn colCode;
    public TableColumn colUnitePrice;
    public TableColumn colQty;
    public TableColumn ColTotal;
    public AnchorPane root;

    QueryBo queryBo= BoFactory.getInstance().getBo(BoType.QUERY);
    public void initialize() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colUnitePrice.setCellValueFactory(new PropertyValueFactory<>("unitePrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        ColTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
    }


    public void loadOrderDetails(String id) {
        try {
            ObservableList<ItemDetailsTm> oblist= FXCollections.observableArrayList();

            ArrayList<CustomDto> arrayList = queryBo.loadItemDetails(id);
            for (CustomDto c:arrayList){
                double tempunitePrice=c.getUnitePrice();
                int temQty=c.getQty();
                double total=tempunitePrice*temQty;

                oblist.add(new ItemDetailsTm(c.getCode(),tempunitePrice,temQty,total));

            }
            tblItem.setItems(oblist);

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
