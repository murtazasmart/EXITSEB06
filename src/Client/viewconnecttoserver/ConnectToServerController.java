package Client.viewconnecttoserver;

import Client.Client;
import Client.MainController;
import Client.viewstartup.StartupController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Created by MA_Laptop on 6/21/2017.
 */
public class ConnectToServerController extends Application{

    public static void main(String[] args) {
        launch();
    }

    @FXML
    Button connectToServerButton;

    public void connectToServerButtonClicked(){
        /*Stage stage = (Stage) connectToServerButton.getScene().getWindow();
        MainController mainController = new MainController(stage);
        Platform.runLater(
                () -> {
                    Thread mainControllerThread = new Thread(mainController);
                    mainControllerThread.run();
                }
        );*/
        ConnectToServerService connectToServerService = new ConnectToServerService();
        Client client = connectToServerService.connectToServer();
        //if(client==null)System.out.println("connecttoserver client is null");else System.out.println("client not null in connect to server");
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

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Client/viewconnecttoserver/connecttoserver.fxml"));
        primaryStage.setTitle("EXIT-POKER");
        primaryStage.setScene(new Scene(root, 1470, 1000));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
