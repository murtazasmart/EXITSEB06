package Client.viewjoin;

import Client.Client;
import Model.ClientThread;

import java.io.IOException;

/**
 * Created by MA_Laptop on 6/21/2017.
 */
public class JoinService {
    Client client;

    public boolean joinGame(){
        ClientThread clientThread = new ClientThread();
        clientThread.setGameName(client.getGameName());
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
