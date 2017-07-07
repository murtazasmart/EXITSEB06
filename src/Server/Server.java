package Server;

import Model.ClientThread;
import Controller.Game;
import Utilities.Constances.DBConfig;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by MA_Laptop on 6/18/2017.
 */
public class Server extends Thread{

    ServerSocket serverSocket;
    Socket clientSocket;
    PrintWriter writeToClient;
    BufferedReader readFromClient;
    ObjectOutputStream sendObjectToClient;
    ObjectInputStream receiveObjectFromClient;
    Object receivedObject;
    InetAddress clientIPAddress, serverIPAddress;
    ClientThread[] clientThreadThreads;
    int maxClientsCount, port, maxGamesCount, currentGamesCount;
    String tablename, password, username;
    Scanner scan;
    Game[] activeGames;
    boolean serverStarted;
    DBConfig dbConfig;

    public Server(String serverIPAddress, int port) {

        try {
            this.serverIPAddress = InetAddress.getByName(serverIPAddress);
            this.port = port;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public Server() {

    }


    public void run() {
        //STARTS THE SERVER BY CONNECTING TO RELEVANT PORT
        startServer();
        //KEEPS LISTENING UNTIL PROCESS ENDED FOR CLIENTS AND CONNECTS THEM TO SERVER. HANDLES THEIR CHOICES IE. CREATE/JOIN GAME ETC.
        connectClients();


    }

    public boolean startServer(){
        try {
            serverSocket = new ServerSocket(port,100,serverIPAddress);
            maxClientsCount = 10;
            clientThreadThreads = new ClientThread[maxClientsCount];
            maxGamesCount = 20;
            activeGames =  new Game[maxGamesCount];
            dbConfig = new DBConfig();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void connectClients() {
        while (true){
            try {
                //Sets up socket and IP to communicate with client and a while loop is used to make it continuous, if not it only waits
                //for one listen instance received from client
                System.out.println("no of active games " + currentGamesCount);
                clientSocket = serverSocket.accept();
                receiveObjectFromClient = new ObjectInputStream(clientSocket.getInputStream());
                sendObjectToClient = new ObjectOutputStream(clientSocket.getOutputStream());
                receivedObject = receiveObjectFromClient.readObject();
                if (receivedObject.getClass() == ClientThread.class) {
                    //Game name received from serverClientThread, if he wants to join he sends a serverClientThread object, GameID will be same as active array val
                    System.out.println("got client thread");
                    ClientThread serverClientThread = (ClientThread) receivedObject;
                    for (int i = 0; i < maxGamesCount; i++) {
                        if (activeGames[i].getGameName().equalsIgnoreCase(serverClientThread.getGameName())) {
                            activeGames[i].addPlayer(serverClientThread, clientSocket, receiveObjectFromClient, sendObjectToClient);
                            if (activeGames[i].getMaxClientsCount() == activeGames[i].getNoOfPlayers())
                                activeGames[i].start();
                            System.out.println("max clients: " + activeGames[i].getMaxClientsCount() + " noOfPlayers is: " + activeGames[i].getNoOfPlayers());
                            break;
                        }
                    }
                    System.out.println("Game joined");
                } else if (receivedObject.getClass() == Game.class) {
                    Game game = (Game) receivedObject;
                    createGame(game);
                    for (int i = 0; i < maxGamesCount; i++) {
                        if (activeGames[i].getGameName().equalsIgnoreCase(game.getGameName())) {
                            activeGames[i].addPlayer(clientSocket, receiveObjectFromClient, sendObjectToClient, game);
                            break;
                        }
                    }
                    System.out.println("game created and player added");
                }
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void createGame(Game gameObject){
        //DONT USE SAME OBJECT FROM CLIENT CAUSE NOT SAFE, CREATE A NEW OBJECT AND ONLY UPDATE RELEVANT DETAILS
        for(int i = 0; i < maxGamesCount; i++){
            System.out.println("game "+i+" is "+activeGames[i]);
            if(activeGames[i]==null || activeGames[i].getGameName().equalsIgnoreCase(gameObject.getGameName()) || activeGames[i].isEnded()){
                gameObject = new Game(gameObject.getGameName(), i, gameObject.getMaxClientsCount());
                activeGames[i] = gameObject;
                addGameToDB(gameObject.getGameName());
                currentGamesCount++;
                break;
            }
        }
    }

    public boolean addGameToDB(String gameName){
        Connection connection = dbConfig.getConnection();
        Statement command = null;
        try {
            command = connection.createStatement();
            command.execute("INSERT INTO waitinggames(`GameName`,`MaxPlayerCount`,`CurrentPlayerCount`) VALUES ('"+gameName+"',0,0)");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
