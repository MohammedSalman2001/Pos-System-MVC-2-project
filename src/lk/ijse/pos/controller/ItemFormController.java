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
import lk.ijse.pos.bo.custom.ItemBo;
import lk.ijse.pos.dao.DaoFactory;
import lk.ijse.pos.dao.DaoType;
import lk.ijse.pos.dao.custom.ItemDao;
import lk.ijse.pos.dao.custom.impl.ItemDaoImpl;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.dto.ItemDto;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.tm.ItemTm;


import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

public class ItemFormController {
    public Label lblDate;
    public JFXTextField txtCode;
    public JFXTextField txtDescription;
    public JFXTextField txtUnitePrice;
    public JFXTextField txtSearch;
    public JFXTextField txtQtyOnHand;
    public JFXButton btnSave;
    public TableView<ItemTm> tblItem;
    public AnchorPane roott;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colUnitePrice;
    public TableColumn colQtyOnHand;
    public TableColumn colOption;

    public  String searchText="";

    ItemBo itemBo= BoFactory.getInstance().getBo(BoType.ITEM);

    public  void initialize(){
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitePrice.setCellValueFactory(new PropertyValueFactory<>("unitePrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        searchItem(searchText);
        setDate();

        tblItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(null!=newValue){
                setValue( newValue);
            }
        });

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText=newValue;
            searchItem(searchText);
        });

    }

    private void setValue(ItemTm tm) {
        txtCode.setText(tm.getCode());
        txtDescription.setText(tm.getDescription());
        txtUnitePrice.setText(String.valueOf(tm.getUnitePrice()));
        txtQtyOnHand.setText(String.valueOf(tm.getQtyOnHand()));
        btnSave.setText("Update");
    }


    public void setDate(){
        Timeline timeline=new Timeline(
                new KeyFrame(Duration.ZERO, e->{
                    DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss");
                    lblDate.setText(LocalDateTime.now().format(formatter));
                }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    public void searchItem(String text){
        String searchText="%"+text+"%";
        ObservableList<ItemTm>obList= FXCollections.observableArrayList();
        try {
           /* PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Item WHERE description LIKE? || code LIKE ?");
            statement.setString(1,searchText);
            statement.setString(2,searchText);
            ResultSet set = statement.executeQuery();*/
            ArrayList<ItemDto> arrayList = itemBo.searchItem(searchText);
            for(ItemDto i:arrayList){
                Button btn=new Button("Delete");
                ItemTm tm=new ItemTm(
                        i.getCode(),
                        i.getDescription(),
                        i.getUnitePrice(),
                        i.getQtyOnHand(),btn

                        );


                obList.add(tm);

                btn.setOnAction(event -> {
                    Alert alert=new Alert(Alert.AlertType.WARNING,"Do you want delete",ButtonType.NO,ButtonType.YES);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if(buttonType.get()==ButtonType.YES){
                        try {
                     /*       PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("DELETE FROM Item WHERE code=?");
                            stm.setString(1,tm.getCode());*/

                            if(itemBo.deleteItem(tm.getCode())){
                                new Alert(Alert.AlertType.INFORMATION,"Customer Deleted").show();
                                searchItem(searchText);
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                });


            }tblItem.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        Item item=new Item(txtCode.getText(),txtDescription.getText(),
                Double.parseDouble(txtUnitePrice.getText()),Integer.parseInt(txtQtyOnHand.getText()));

        if(btnSave.getText().equalsIgnoreCase("Save")){

            try {
              boolean isSave=  itemBo.saveItem(new ItemDto(txtCode.getText(),txtDescription.getText(),
                        Double.parseDouble(txtUnitePrice.getText()),Integer.parseInt(txtQtyOnHand.getText())));


                if(isSave){
                    searchItem(searchText);
                   clearAll();
                    new Alert(Alert.AlertType.INFORMATION,"Item Added").show();
                }else{
                    new Alert(Alert.AlertType.WARNING,"Try Again").show();

                }

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }





        }else {
            try {


                boolean isUpdate =itemBo.updateItem(new ItemDto(txtCode.getText(),txtDescription.getText(),
                        Double.parseDouble(txtUnitePrice.getText()),Integer.parseInt(txtQtyOnHand.getText())));
                if(isUpdate){
                    searchItem(searchText);
                   clearAll();
                    new Alert(Alert.AlertType.INFORMATION,"Customer Update").show();
                }else {
                    new Alert(Alert.AlertType.WARNING,"Try again").show();
                }

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

        }



    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        btnSave.setText("Save");
        clearAll();
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage= (Stage) roott.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/MenuForm.fxml"))));
    }

    public  void clearAll(){
        txtUnitePrice.clear();
        txtCode.clear();
        txtQtyOnHand.clear();
        txtDescription.clear();
    }
}
