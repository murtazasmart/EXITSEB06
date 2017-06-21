package Client;

import Client.viewhost.HostController;
import Client.viewstartup.StartupController;
import javafx.stage.Stage;

/**
 * Created by MA_Laptop on 6/18/2017.
 */
public class MainClient implements Runnable{

    public static void main(String[] args) {
        System.out.println("ehrehr");
        MainClient mc = new MainClient();
        Thread t1 = new Thread(mc);
        t1.start();
    }

    @Override
    public void run() {
//        HostController hostController = new HostController();
//        hostController.startView();
//        StartupController startupController = new StartupController();
//        startupController.start();

        /*Thread t1 = new Thread(hostController);
        t1.run();
        System.out.println("im not in sync method yet");
        synchronized (t1){
            try {
                System.out.println("im sync in main");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(hostController.getServerIPAddress());
        }*/
    }
}
