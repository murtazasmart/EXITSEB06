package Server;

import Client.viewhost.HostController;
import Model.*;

/**
 * Created by MA_Laptop on 6/18/2017.
 */
public class MainServer {
    public static void main(String[] args) {
        //only for testing
        Server server = new Server("192.168.1.103",4444);
        server.start();
//        HostController hostController = new HostController();
//        hostController.startView();
    }
}
