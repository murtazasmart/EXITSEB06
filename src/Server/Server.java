package Server;

import Model.ClientThread;
import Controller.Game;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
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
        //Creates ports and add clients if needed
        /*HostController hostController = new HostController();
        hostController.startView();
        while(true){
            if(hostController.getServerIPAddress()!=null || hostController.getServerIPAddress().equalsIgnoreCase("")){
                System.out.println("from server"+hostController.getServerIPAddress());
                break;
            }
        }
        try {
            serverIPAddress = InetAddress.getByName(hostController.getServerIPAddress());
            port = hostController.getPort();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }*//*
        HostController hostController = new HostController();
        Thread t1 = new Thread(hostController);
        t1.start();
        synchronized (hostController){
            try {
                wait();
                System.out.println("YELLOW");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
        startServer();
        connectClients();


    }

    public boolean startServer(){

        try {
            //System.out.println("Enter IP address of Host:");
            //scan = new Scanner(System.in);
            //serverIPAddress = InetAddress.getByName(scan.nextLine());
            //System.out.println("Enter port number:");
            //port = scan.nextInt();
            serverSocket = new ServerSocket(port,100,serverIPAddress);
            maxClientsCount = 10;
            clientThreadThreads = new ClientThread[maxClientsCount];
            maxGamesCount = 20;
            activeGames =  new Game[maxGamesCount];
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
                //ServerSocket class creates a socket whereas the socket class just makes it possible to listen to an already existing socket


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
/*
    //VIEW CODE
    @FXML
    TextField hostIPAddress;
    @FXML
    TextField hostPortNo;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Client/viewhost/host.fxml"));
        primaryStage.setTitle("EXIT-POKER");
        primaryStage.setScene(new Scene(root));
        primaryStage.setFullScreen(true);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public void iveBeenClicked(ActionEvent actionEvent) {
        System.out.println("Here clcikeed");
        InetAddressValidator inetAddressValidator =
                InetAddressValidator.getInstance();

        System.out.println(hostIPAddress.getText() + "  " + inetAddressValidator.isValid(hostIPAddress.getText()));
        if(inetAddressValidator.isValid(hostIPAddress.getText())){
            try {
                serverIPAddress = InetAddress.getByName(hostIPAddress.getText());
                port = 4444;
                System.out.println(Integer.parseInt(hostPortNo.getText()));
                serverStarted = startServer();
                if(serverStarted){
                    startJoin();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //JoinController joinController = new JoinController();
        //joinController.start(stage);

    }

    public void startJoin(){
        Stage stage = (Stage) hostIPAddress.getScene().getWindow();
        /*JoinController joinController = new JoinController();
        joinController.start(stage);
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/Client/viewjoin/join.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.setMaximized(true);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }*/
}
