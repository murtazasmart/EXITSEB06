package GUI.ViewController;

import Model.Client;
import GUI.Services.JoinService;
import Server.Server;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Bhagya Rathnayake on 6/17/2017.
 */
public class JoinController extends Application{

    @FXML
    Button btnJoin, btnJoinBack;
    @FXML
    TextField joinTextField;

    private String serverIPAddress;
    Client client;

    Thread mainClientThread;

    public void btnJoinClicked(){
        client.setGameName(joinTextField.getText());
        JoinService joinService = new JoinService();
        client.setUsername("mustafa");
        joinService.setClient(client);
        boolean gameJoined = joinService.joinGame();
        if(gameJoined){
            Stage stage = (Stage)btnJoin.getScene().getWindow();
            WaitingRoomController waitingRoomController = new WaitingRoomController();
            waitingRoomController.setClient(client);
            try {
                waitingRoomController.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void iveBeenClicked(){
        System.out.println("Here clcikeed");
        Stage stage = (Stage)joinTextField.getScene().getWindow();
        serverIPAddress = joinTextField.getText();
        System.out.println(serverIPAddress);
        if(serverIPAddress != null)
            System.out.println("ip not null");
        Server server = new Server();

        new Thread(() -> {
            mainClientThread = new Thread(server);
            server.run();
        }).start();

    }

    public void btnBackClicked(){
        Stage stage = (Stage)btnJoinBack.getScene().getWindow();
        StartupController startupController = new StartupController();
        startupController.setClient(client);
        startupController.start(stage);
    }

    public void start(Stage stage){
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/join.fxml"));
            loader.setController(this);
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("EXIT-POKER");
        stage.setScene(new Scene(root, 1470, 1000));
        stage.show();
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}

