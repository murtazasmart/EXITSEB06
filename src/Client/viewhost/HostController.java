package Client.viewhost;

import Server.Server;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by Bhagya Rathnayake on 6/17/2017.
 */
public class HostController extends Application implements Runnable{

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
        primaryStage.show();
    }

    public void iveBeenClicked(){
        synchronized (this){
            System.out.println("Here clcikeed");
            Stage stage = (Stage)hostIPAddress.getScene().getWindow();
            serverIPAddress = hostIPAddress.getText();
            port = Integer.parseInt(hostPortNo.getText());
            System.out.println(serverIPAddress + port);
            Server server = new Server(serverIPAddress,port);
            notifyAll();
            //stage.close();
        }

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

    @Override
    public void run() {
        startView();
    }
}
