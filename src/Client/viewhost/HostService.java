package Client.viewhost;

import Client.Client;
import Model.ClientThread;
import Model.Game;
import Model.Message;
import Model.Player;

import java.io.IOException;
import java.net.UnknownHostException;

/**
 * Created by MA_Laptop on 6/21/2017.
 */
public class HostService {
    Client client;



    public boolean hostGame(){
        try {
                Game game = new Game();
                game.setGameName(client.getGameName());
                game.setMaxClientsCount(client.getNumberOfPlayers());
                client.getSendObjectToServer().writeObject(game);
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
