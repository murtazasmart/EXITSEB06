package Client.viewhost;

import Client.TrialServiceClass;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;

/**
 * Created by Bhagya Rathnayake on 6/17/2017.
 */
public class HostController extends Application implements Runnable, Initializable {

    String serverIPAddress;
    int port;

    @FXML
    TextField hostIPAddress;
    @FXML
    TextField hostPortNo;

    public void startView(){
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("host.fxml"));
        primaryStage.setTitle("EXIT-POKER");
        primaryStage.setScene(new Scene(root, 1470, 1000));
        primaryStage.setMaximized(true);
//        task.run();
        primaryStage.show();
    }

    public void iveBeenClicked(){
            System.out.println("Here clcikeed");
            Stage stage = (Stage)hostIPAddress.getScene().getWindow();
            serverIPAddress = hostIPAddress.getText();
            port = Integer.parseInt(hostPortNo.getText());
            System.out.println(serverIPAddress + port);
            if(serverIPAddress != null)
                System.out.println("ip not null");
            //stage.close();

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

    @Override
    public void run() {
        //System.out.println("im in run");
        /*new Thread(() -> {
            startView();
        }).start();*//*
        Executors.newSingleThreadExecutor().submit(() -> {
            startView();
        });*/
        /*Platform.runLater(new Runnable() {
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
        });*/

    }

    Task task = new Task<Void>() {


        @Override public Void call() {
            System.out.println("task has started");
            System.out.println(serverIPAddress);
            while(true) {
                if (serverIPAddress != null) {
                    System.out.println("finally got a break");
                    break;
                }
            }
            System.out.println("ive broke n from loop");
            return null;
        }
    };

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        new Thread(task).start();

    }

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
}
