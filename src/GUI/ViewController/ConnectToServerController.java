package GUI.ViewController;

import Model.Client;
import GUI.Services.ConnectToServerService;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

/**
 * Created by MA_Laptop on 6/21/2017.
 */
public class ConnectToServerController extends Application{

    Client client;

    public static void main(String[] args) {
        launch();
    }

    @FXML
    Button connectToServerButton;

    public void connectToServerButtonClicked(){
        client = new Client();
        InetAddress ipAddress = null;
        TextInputDialog dialog = new TextInputDialog("Enter local server IP");
        dialog.setTitle("Local server IP");
        dialog.setHeaderText("Local server IP");
        dialog.setContentText("Enter local server IP");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            System.out.println("Local IP: " + result.get());
        }

        try {
            ipAddress = InetAddress.getByName(result.get());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        ConnectToServerService connectToServerService = new ConnectToServerService();
        client.setIpAddress(ipAddress);
        connectToServerService.setClient(client);

        Runnable task2 = () -> {
            client = connectToServerService.connectToServerLocalServer();
            Platform.runLater(() -> {
                if(client != null){
                    Stage stage = (Stage)connectToServerButton.getScene().getWindow();
                    StartupController startupController = new StartupController();
                    startupController.setClient(client);
                    try {
                        startupController.start(stage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        };

        // start the thread
        new Thread(task2).start();

    }

    public void connectToGlobalServerButtonClicked(){
        ConnectToServerService connectToServerService = new ConnectToServerService();

        Runnable task2 = () -> {
            client = connectToServerService.connectToServerGlobalServer();
            Platform.runLater(() -> {
                if(client != null){
                    Stage stage = (Stage)connectToServerButton.getScene().getWindow();
                    StartupController startupController = new StartupController();
                    startupController.setClient(client);
                    try {
                        startupController.start(stage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        };

        // start the thread
        new Thread(task2).start();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/View/connecttoserver.fxml"));
        primaryStage.setTitle("EXIT-POKER");
        primaryStage.setScene(new Scene(root, 1470, 1000));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
