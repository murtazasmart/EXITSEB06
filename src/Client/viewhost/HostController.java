package Client.viewhost;

import Client.Client;
import Client.TrialServiceClass;
import Client.viewwaitingroom.WaitingRoomController;
import Server.Server;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;

/**
 * Created by Bhagya Rathnayake on 6/17/2017.
 */
public class HostController extends Application {

    String serverIPAddress;
    int port;
    Client client;

    @FXML
    TextField hostGameName, hostNoPlayers;
    @FXML
    Button btnHostGame;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("host.fxml"));
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
        client.setUsername("murtaza");
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

//    public void iveBeenClicked(){
//            System.out.println("Here clcikeed");
//            Stage stage = (Stage)hostIPAddress.getScene().getWindow();
//            serverIPAddress = hostIPAddress.getText();
//            port = Integer.parseInt(hostPortNo.getText());
//            System.out.println(serverIPAddress + port);
//            if(serverIPAddress != null)
//                System.out.println("ip not null");
//            //stage.close();
//
//    }

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

    /*@Override
    public void init() throws Exception{
        System.out.println("init");
        Platform.runLater(new Runnable() {
            @Override
            public void run(){
                System.out.println("im in run");
                synchronized (this){
                    while(true){
                        if(serverIPAddress !=null){
                            break;
                        }
                    }
                    System.out.println("ive broke n from loop");
                    notifyAll();
                    //stage.close();
                }
            }
        });
    }*/

//    @Override
//    public void run() {
//        //System.out.println("im in run");
//        /*new Thread(() -> {
//            startView();
//        }).start();*//*
//        Executors.newSingleThreadExecutor().submit(() -> {
//            startView();
//        });*/
//        /*Platform.runLater(new Runnable() {
//            @Override
//            public void run(){
//                System.out.println("im in run");
//                synchronized (this){
//                    while(true){
//                        if(serverIPAddress !=null){
//                            break;
//                        }
//                    }
//                    System.out.println("ive broke n from loop");
//                    notifyAll();
//                    //stage.close();
//                }
//            }
//        });*/
//
//    }
//
//    Task task = new Task<Void>() {
//
//
//        @Override public Void call() {
//            System.out.println("task has started");
//            System.out.println(serverIPAddress);
//            while(true) {
//                if (serverIPAddress != null) {
//                    System.out.println("finally got a break");
//                    break;
//                }
//            }
//            System.out.println("ive broke n from loop");
//            return null;
//        }
//    };
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//
//        new Thread(task).start();
//
//    }

    /*public void runTask(){
        System.out.println("hello");
    }

    Service<ObservableList<Long>> service = new Service<ObservableList<Long>>()
    {
        @Override
        protected Task<ObservableList<Long>> createTask()
        {
            return new TrialServiceClass();
        }
    };*/


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
