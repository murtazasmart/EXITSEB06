package GUI.ViewController;

import Model.Client;
import javafx.application.Application;
import javafx.event.ActionEvent;
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
public class HelpController extends Application{
    Client client;
    @FXML
    Button btnHome;

    public void btnHomeClicked(ActionEvent actionEvent) {
        Stage stage = (Stage)btnHome.getScene().getWindow();
        StartupController startupController = new StartupController();
        startupController.setClient(client);
        startupController.start(stage);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/View/help.fxml"));
        primaryStage.setTitle("EXIT-POKER");
        primaryStage.setScene(new Scene(root));
//        primaryStage.setResizable(false);
//        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }
}
