package Server;

/**
 * Created by MA_Laptop on 6/18/2017.
 */
public class MainServer {
    public static void main(String[] args) {
        //only for testingtt
        Server server = new Server("169.254.51.167",4445);
        server.start();
//        HostController hostController = new HostController();
//        hostController.startView();
    }
}
