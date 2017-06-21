package Client.viewjoin;

import Model.Game;
import Server.Server;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Bhagya Rathnayake on 6/17/2017.
 */
public class JoinController {

    @FXML
    Button joinButton;

    @FXML
    Button hostButton;

    @FXML
    TextField joinTextField;
    private String serverIPAddress;

    Thread t1;

    public void hostHasBeenClicked(){
        System.out.println("Host clicked");
    }

    public void iveBeenClicked(){
        System.out.println("Here clcikeed");
        Stage stage = (Stage)joinTextField.getScene().getWindow();
        serverIPAddress = joinTextField.getText();
        System.out.println(serverIPAddress);
        if(serverIPAddress != null)
            System.out.println("ip not null");
        Server server = new Server();
       /* t1 = new Thread(server);
        server.run();*/

        new Thread(() -> {
            t1 = new Thread(server);
            server.run();
        }).start();
        //stage.close();

    }

    /*public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("join.fxml"));
        primaryStage.setTitle("EXIT-POKER");
        primaryStage.setScene(new Scene(root, 1470, 1000));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }*/
}

