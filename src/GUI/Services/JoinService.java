package GUI.Services;

import Model.Client;
import Model.ClientThread;
import Utilities.Constances.DBConfig;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Created by MA_Laptop on 6/21/2017.
 */
public class JoinService {
    Client client;

    public boolean joinGame(){
        DBConfig dbConfig = new DBConfig();
        Connection connection = dbConfig.getConnection();
        Statement command = null;
        ResultSet resultSet = null;
        try {
            command = connection.createStatement();
            resultSet = command.executeQuery("SELECT * FROM waitnggames WHERE GameName='"+client.getGameName()+"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(resultSet==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Game Name");
            alert.setHeaderText("Such game hasn't been hosted");
            alert.setContentText("Go to host game to make a new game!?");

            alert.showAndWait();
            return false;
        }

        ClientThread clientThread = new ClientThread();
        clientThread.setGameName(client.getGameName());
//        Scanner scan = new Scanner(System.in);
        clientThread.setUsername(client.getUsername());
        try {
            client.getSendObjectToServer().writeObject(clientThread);
            client.getSendObjectToServer().flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
