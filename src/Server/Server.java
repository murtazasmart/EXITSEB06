package Server;

import Client.viewhost.HostController;
import Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by MA_Laptop on 6/18/2017.
 */
public class Server extends Application{

    ServerSocket serverSocket;
    Socket clientSocket;
    PrintWriter writeToClient;
    BufferedReader readFromClient;
    ObjectOutputStream sendObjectToClient;
    ObjectInputStream receiveObjectFromClient;
    Object receivedObject;
    KnockKnockProtocol kkp;
    InetAddress clientIPAddress, serverIPAddress;
    ClientThread[] clientThreadThreads;
    int maxClientsCount, port, maxGamesCount, currentGamesCount;
    String tablename, password, username;
    Scanner scan;
    Game[] activeGames;


    public void run() {
        //Creates ports and add clients if needed
        startServer();
        connectClients();

    }

    public void startServer(){

        try {
            launch();
            System.out.println("Enter IP address of Host:");
            scan = new Scanner(System.in);
            serverIPAddress = InetAddress.getByName(scan.nextLine());
            System.out.println("Enter port number:");
            port = scan.nextInt();
            serverSocket = new ServerSocket(port,100,serverIPAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }
        maxClientsCount = 10;
        clientThreadThreads = new ClientThread[maxClientsCount];
        maxGamesCount = 20;
        activeGames =  new Game[maxGamesCount];
    }

    public void connectClients(){
        try {
            //Sets up socket and IP to communicate with client and a while loop is used to make it continuous, if not it only waits
            //for one listen instance received from client
            //ServerSocket class creates a socket whereas the socket class just makes it possible to listen to an already existing socket

            while(true){
                System.out.println("no of active games "+currentGamesCount);
                clientSocket = serverSocket.accept();
                receiveObjectFromClient = new ObjectInputStream(clientSocket.getInputStream());
                sendObjectToClient = new ObjectOutputStream(clientSocket.getOutputStream());
                receivedObject = receiveObjectFromClient.readObject();
                if(receivedObject.getClass() == ClientThread.class){
                    //Game name received from serverClientThread, if he wants to join he sends a serverClientThread object, GameID will be same as active array val
                    System.out.println("got client thread");
                    ClientThread serverClientThread = (ClientThread)receivedObject;
                    for(int i = 0; i < maxGamesCount; i++){
                        if(activeGames[i].getGameName().equalsIgnoreCase(serverClientThread.getGameName())){
                            activeGames[i].addPlayer(serverClientThread, clientSocket, receiveObjectFromClient, sendObjectToClient);
                            if(activeGames[i].getMaxClientsCount() == activeGames[i].getNoOfPlayers())
                                activeGames[i].start();
                            System.out.println("max clients: "+activeGames[i].getMaxClientsCount()+" noOfPlayers is: "+activeGames[i].getNoOfPlayers());
                            break;
                        }
                    }
                    System.out.println("Game joined");
                }
                else if(receivedObject.getClass() == Game.class){
                    Game game = (Game)receivedObject;
                    createGame(game);
                    for(int i = 0; i < maxGamesCount; i++){
                        if(activeGames[i].getGameName().equals(game.getGameName())){
                            activeGames[i].addPlayer(clientSocket, receiveObjectFromClient, sendObjectToClient);

                        }
                        break;
                    }
                    System.out.println("game created and player added");
                }


            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void createGame(Game gameObject){
        //DONT USE SAME OBJECT FROM CLIENT CAUSE NOT SAFE, CREATE A NEW OBJECT AND ONLY UPDATE RELEVANT DETAILS
        for(int i = 0; i < maxGamesCount; i++){
            if(activeGames[i]==null){
                //Thread t = new Thread client();
                gameObject = new Game(gameObject.getGameName(), i, gameObject.getMaxClientsCount());
                //Store game details in DB
                //GameID will be same as active array val
                //CANNOT START GAME UNTIL EVERYONE IS OKAY
                activeGames[i] = gameObject;
                currentGamesCount++;
                //activeGames[i].start();
                break;
            }
        }
    }

    public void identifyAction(ObjectInputStream receiveObjectFromClient){
        try {
            //We can check the received object, if new ClientThread object then can call addPlayer() if not update
            //the state of the existing player and send it back


            //NOTE For each while loop the class only checks once, so example client sends two objects cons
            //This checks which class has been sent and then assigns it to the appropriate one
            receivedObject = receiveObjectFromClient.readObject();
            /*if(receivedObject.getClass() == ClientThread.class){
                clientThreadThreads = (ClientThread)receivedObject;
                System.out.println(p.getUsername()+" in ClientThread");
            }
            else if( receivedObject.getClass() == KnockKnockProtocol.class){
                kkp = (KnockKnockProtocol)receivedObject;
                System.out.println(kkp.getText()+"in KKP");
            }*/
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Client/viewhost/host.fxml"));
        primaryStage.setTitle("EXIT-POKER");
        primaryStage.setScene(new Scene(root, 1470, 1000));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public void iveBeenClicked(){
        System.out.println("Here clcikeed");
    }
}
