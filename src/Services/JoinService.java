package Services;

import Client.Client;
import Model.ClientThread;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by MA_Laptop on 6/21/2017.
 */
public class JoinService {
    Client client;

    public boolean joinGame(){
        ClientThread clientThread = new ClientThread();
        clientThread.setGameName(client.getGameName());
        Scanner scan = new Scanner(System.in);
        clientThread.setUsername(scan.next());
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
