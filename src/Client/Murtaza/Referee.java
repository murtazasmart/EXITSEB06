package Client.Murtaza;

import java.util.Scanner;

/**
 * Created by MA_Laptop on 5/28/2017.
 */
public class Referee {
    Scanner scan;
    //This class will only help users deicde if they want to host a game of join a game.
    //If they host their IP address has to be shown and the port they connected to
    //If they join they should give the connecting IP address and port

    public static void main(String arsg[]) throws ClassNotFoundException {

    }

    public void Moderator(){
        System.out.println("Do you wanna host or join(h/j):");
        scan = new Scanner(System.in);
        String result = scan.nextLine();
        if(result.equalsIgnoreCase("H")){
            Server hostServer = new Server();
            Thread serverThread = new Thread(hostServer);
            serverThread.start();
        }
        else if(result.equalsIgnoreCase("J")){
           /* ClientThread joinServer = new ClientThread();
            Thread clientThread = new Thread(joinServer);
            clientThread.start();*/
        }
    }
}
