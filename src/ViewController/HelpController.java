package ViewController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Bhagya Rathnayake on 6/13/2017.
 */
public class HelpController {

    Thread mainClientThread;

    @FXML
    Button helpMyButton;

    public void myButtonClicked(){
        System.out.println("my button clicked");
        Stage stage = (Stage) helpMyButton.getScene().getWindow();
        JoinController joinController = new JoinController();
        joinController.start(stage);
    }

    public void start(Stage stage, Thread mainClientThread){
        Parent root = null;
        try {
            this.mainClientThread = mainClientThread;
            root = FXMLLoader.load(getClass().getResource("/View/help.fxml"));
            stage.setTitle("EXIT-POKER");
            stage.setScene(new Scene(root, 1470, 1000));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*@Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/help.fxml"));
        primaryStage.setTitle("EXIT-POKER");
        primaryStage.setScene(new Scene(root, 1470, 1000));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }*/
}
