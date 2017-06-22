package Client.viewwaitingroom;

import Client.Client;
import Model.Message;

import java.io.IOException;

/**
 * Created by MA_Laptop on 6/21/2017.
 */
public class WaitingRoomService {

    Client client;

    public boolean waitngForAllPlayers(){
        Message message = null;
        try {
            message = (Message)client.getReceiveObjectFromServer().readObject();
            System.out.println(message.getText());
            while(true){
                message = (Message)client.getReceiveObjectFromServer().readObject();
                System.out.println(message.getText());
                if(message.getText().equalsIgnoreCase("Game is starting"))
                    break;
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
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
