package Client;

import Model.*;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Created by MA_Laptop on 6/18/2017.
 */
public class Client {

    Socket clientSocket;
    PrintWriter writeToServer;
    BufferedReader readFromServer;
    ObjectOutputStream sendObjectToServer;
    ObjectInputStream receiveObjectFromServer;
    InetAddress ipAddress;
    String username;
    int score, gameID;
    String gameName;

    public static void main(String[] args) {
        Client client = new Client();
        client.joinGame();
    }

    public void joinGame(){
        try {
            System.out.println("Enter IP of host followed:");
            Scanner scan = new Scanner(System.in);
            ipAddress = InetAddress.getByName(scan.nextLine());
            clientSocket = new Socket(ipAddress, 4444);
            System.out.println("connected?");
            sendObjectToServer = new ObjectOutputStream(clientSocket.getOutputStream());
            receiveObjectFromServer = new ObjectInputStream(clientSocket.getInputStream());
            System.out.println("streams?");
            //Scanner scan = new Scanner(System.in);
            System.out.println("do u wanna join or create game(c/j)");
            String res = scan.next();
            if(res.equalsIgnoreCase("c")){
                Game game = new Game();
                System.out.println("Enter game name");
                game.setGameName(scan.next());
                System.out.println("Enter number of players");
                game.setMaxClientsCount(scan.nextInt());
                //game.gameName = "garena";
                sendObjectToServer.writeObject(game);
                sendObjectToServer.flush();
            }
            else if(res.equalsIgnoreCase("j")){
                ClientThread ct = new ClientThread();
                System.out.println("Enter game name");
                ct.setGameName(scan.next());
                //ct.gameName="garena";
                sendObjectToServer.writeObject(ct);
                sendObjectToServer.flush();
            }
            Message message = (Message)receiveObjectFromServer.readObject();
            System.out.println(message.getText());
            while(true){
                message = (Message)receiveObjectFromServer.readObject();
                System.out.println(message.getText());
                if(message.getText().equalsIgnoreCase("Game is starting"))
                    break;
            }
            System.out.println("ive broken out!!");
            while(true){
                message = (Message)receiveObjectFromServer.readObject();
                System.out.println(message.getText());
                Player player = new Player();
                player.setScore(scan.nextInt());
                sendObjectToServer.reset();
                sendObjectToServer.writeObject(player);
                sendObjectToServer.flush();
                message = (Message)receiveObjectFromServer.readObject();
                System.out.println(message.getText());
            }

            /*
            writeToServer = new PrintWriter(clientSocket.getOutputStream(), true);
            readFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String fromServer;
            String fromUser;

            sendObjectToServer = new ObjectOutputStream(clientSocket.getOutputStream());
            receiveObjectFromServer = new ObjectInputStream(clientSocket.getInputStream());

            KnockKnockProtocol kkp = (KnockKnockProtocol)receiveObjectFromServer.readObject();
            System.out.println(kkp.getText());


            //CAN ONLY SEND 1 OBJECT AT A TIME
            kkp = new KnockKnockProtocol("I Came from client");
            sendObjectToServer.writeObject(kkp);
            sendObjectToServer.flush();

            //WRITE CODE TO GET HOST AND PLAYER IPs ONCE OBJECT SENT TO SERVER THE CLIENT SHOULD THEN LISTEN FOR SERVER AND WAIT TILL IT SENDS COMMANDS


            /*while ((fromServer = in.readLine()) != null) {
                System.out.println("Server: " + fromServer);
                if (fromServer.equals("Bye."))
                    break;

                fromUser = stdIn.readLine();
                if (fromUser != null) {
                    System.out.println("ClientThread: " + fromUser);
                    out.println(fromUser);
                }
            }*/
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host ");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to ");
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}