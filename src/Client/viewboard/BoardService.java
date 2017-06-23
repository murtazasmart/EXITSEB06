package Client.viewboard;

import Client.Client;
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

    public boolean sendPlayerToServer(Player player){
        try {
            client.getSendObjectToServer().writeObject(player);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

//    public Player getPlayerFromServerRoundB(){
//        Player player = null;
//        try {
//            player = (Player) client.getReceiveObjectFromServer().readObject();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return player;
//    }
//
//    public boolean sendPlayerToServerRoundB(Player player){
//        try {
//            client.getSendObjectToServer().writeObject(player);
//            return true;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    public Player getPlayerFromServerFinalRound(){
//        Player player = null;
//        try {
//            player = (Player) client.getReceiveObjectFromServer().readObject();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return player;
//    }

    public boolean sendMessageToServer(Message message){
        try {
            client.getSendObjectToServer().writeObject(message);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    //getmessage

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
