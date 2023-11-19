package lk.ijse.pos.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

public class SplashScreenController {
    public Label lblLoading;
    public Rectangle rctContainer;
    public Rectangle recLoading;
    public AnchorPane root;

    public void initialize(){
        Timeline timeline=new Timeline();

        KeyFrame keyFrame1= new KeyFrame(Duration.millis(500),actionEvent ->{
            lblLoading.setText("Initializing Application....");
            recLoading.setWidth(rctContainer.getWidth()*0.3);
        });
        KeyFrame keyFrame2= new KeyFrame(Duration.millis(1000),actionEvent ->{
            lblLoading.setText("Loading Internal Resources....");
            recLoading.setWidth(rctContainer.getWidth()*0.5);
        });
        KeyFrame keyFrame3= new KeyFrame(Duration.millis(1500),actionEvent ->{
            lblLoading.setText("Loading Images....");
            recLoading.setWidth(rctContainer.getWidth()*0.6);
        });
        KeyFrame keyFrame4= new KeyFrame(Duration.millis(2000),actionEvent ->{
            lblLoading.setText("Loading UIs....");
            recLoading.setWidth(rctContainer.getWidth()*0.8);
        });
        KeyFrame keyFrame5= new KeyFrame(Duration.millis(2500),actionEvent ->{
            lblLoading.setText("Welcome to LMS v1.0.0");
            recLoading.setWidth(rctContainer.getWidth());
        });

        KeyFrame keyFrame6= new KeyFrame(Duration.millis(3000),actionEvent ->{

            Stage stage= (Stage) root.getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashBoardForm.fxml"))));
            } catch (IOException e) {
                e.printStackTrace();
            }


        });

        timeline.getKeyFrames().addAll(keyFrame1,keyFrame2,keyFrame3,keyFrame4,keyFrame5,keyFrame6);
        timeline.playFromStart();
    }

}
