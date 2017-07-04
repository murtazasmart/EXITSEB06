package GUI.ViewController;

import Model.Client;
import GUI.Services.HostService;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by Bhagya Rathnayake on 6/17/2017.
 */
public class HostController extends Application {

    String serverIPAddress;
    int port;
    Client client;

    @FXML
    TextField hostGameName, hostNoPlayers, txtYourName;
    @FXML
    Button btnHostGame, btnHostGameBack;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/host.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        primaryStage.setTitle("EXIT-POKER");
        primaryStage.setScene(new Scene(root, 1470, 1000));
        primaryStage.show();
    }

    public void btnHostClicked(){
        System.out.println("game name is "+ hostGameName.getText());
        if(client==null)System.out.println("game is null");else System.out.println("client not null");
        client.setGameName(hostGameName.getText());
        client.setNumberOfPlayers(Integer.parseInt(hostNoPlayers.getText()));
        client.setUsername(txtYourName.getText());
        HostService hostService = new HostService();
        hostService.setClient(client);
        boolean gameHosted = hostService.hostGame();
        System.out.println("game host "+gameHosted);
        if(gameHosted){
            Stage stage = (Stage)btnHostGame.getScene().getWindow();
            WaitingRoomController waitingRoomController = new WaitingRoomController();
            waitingRoomController.setClient(client);
            try {
                waitingRoomController.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void btnBackClicked(){
        Stage stage = (Stage)btnHostGameBack.getScene().getWindow();
        StartupController startupController = new StartupController();
        startupController.setClient(client);
        startupController.start(stage);
    }

    public String getServerIPAddress() {
        return serverIPAddress;
    }

    public void setServerIPAddress(String serverIPAddress) {
        this.serverIPAddress = serverIPAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
