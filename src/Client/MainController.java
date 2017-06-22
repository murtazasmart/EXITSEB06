package Client;

import Client.viewstartup.StartupController;
import javafx.stage.Stage;

/**
 * Created by MA_Laptop on 6/20/2017.
 */
public class MainController implements Runnable{

    Stage stage;
    Thread mainClientThread;
    Client client;
    StartupController startupController;
    GUIThread guiThread;

    public MainController() {
        guiThread = new GUIThread();
        guiThread.startView();
    }

    public static void main(String[] args) {
        MainController mainController = new MainController();
        Thread t1 = new Thread(mainController);
        //t1.start();
    }

    public MainController(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void run() {
        client = new Client();
        boolean successfullyConnected = client.connectToServer();
        if(successfullyConnected){
            startupController = new StartupController();
            //startupController.start(stage, this);
        }
        System.out.println("im in main controller run");
    }

}
