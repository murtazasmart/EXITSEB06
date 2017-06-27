package GUI.Services;

import Model.Client;
import Model.Message;
import Model.Player;

import java.io.IOException;

/**
 * Created by MA_Laptop on 6/21/2017.
 */
public class BoardService {

    Client client;

    public Player getPlayerFromServer(){
        Player player = null;
        try {
            player = (Player) client.getReceiveObjectFromServer().readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return player;
    }

    public Message getMessageFromServer(){
        Message message = null;
        try {
            message = (Message) client.getReceiveObjectFromServer().readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return message;
    }

    public boolean sendPlayerToServer(Player player){
        try {
            client.getSendObjectToServer().writeObject(player);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean sendMessageToServer(Message message){
        try {
            client.getSendObjectToServer().writeObject(message);
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
