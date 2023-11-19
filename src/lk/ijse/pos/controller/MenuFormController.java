package lk.ijse.pos.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MenuFormController {
    public Label lblTimeDate;
    public AnchorPane root;

    public void initialize(){
        Timeline timeline=new Timeline(
                new KeyFrame(Duration.ZERO, e->{
                    DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss");
                    lblTimeDate.setText(LocalDateTime.now().format(formatter));
                }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void customerOnAction(ActionEvent actionEvent) throws IOException {
        setUi("CustomerForm");

    }


    public void setUi(String location) throws IOException {
        Stage stage= (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));


    }

    public void btnItemOnAction(ActionEvent actionEvent) throws IOException {
        setUi("ItemForm");
    }

    public void btnOrderOnAction(ActionEvent actionEvent) throws IOException {
        setUi("OrderFrom");
    }

    public void btnOrderDetailsOnAction(ActionEvent actionEvent) throws IOException {
        setUi("OrderTableForm");
    }
}
