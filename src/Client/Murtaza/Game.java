package Client.Murtaza;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by MA_Laptop on 5/28/2017.
 */
public class Game  extends Thread implements Serializable{
    int scoreTick;
    boolean isStarted = false;
    String gameName;
    int gameID, totalScore;
    ClientThread[] clientThreadThreads;
    Player[] players;
    int maxClientsCount = 10;
    boolean isStart = false;
    Scanner scan;
    Message message;
    int noOfPlayers;
    Object object;

    public Game(){
        maxClientsCount = 10;
    }

    public Game(String gameName, int gameID, int maxClientsCount) {
        this.gameName = gameName;
        this.gameID = gameID;
        this.maxClientsCount = maxClientsCount;
        clientThreadThreads = new ClientThread[maxClientsCount];
        players = new Player[maxClientsCount];
        scan = new Scanner(System.in);
        message = new Message();
    }

    @Override
    public void run(){
        startGame();
        gameMainLogic();

    }

    public void addPlayer(ClientThread clientThread, Socket clientSocket, ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream){
        for(int i = 0; i < maxClientsCount; i++){
            if(clientThreadThreads[i]==null){
                //ClientThread object is sent to update the
                clientThread.clientSocket = clientSocket;
                clientThread.receiveObjectFromClient = objectInputStream;
                clientThread.sendObjectToClient = objectOutputStream;
                //Thread t = new Thread clientThread();
                (clientThreadThreads[i] = clientThread).start();
                players[i] = new Player(clientThread);
                noOfPlayers++;
                System.out.println("here1");
                clientThreadThreads[i].sendObjectToClient(new Message("uve been added"));
                break;
            }
        }
        //informAllPlayers("player added");
        for(int i = 0; i < noOfPlayers ; i++){
            clientThreadThreads[i].sendObjectToClient(new Message("player added"));
        }
    }

    public void addPlayer(Socket clientSocket, ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream) {
        for(int i = 0; i < maxClientsCount; i++){
            if(clientThreadThreads[i]==null){
                ClientThread clientThread = new ClientThread();
                clientThread.clientSocket = clientSocket;
                clientThread.receiveObjectFromClient = objectInputStream;
                clientThread.sendObjectToClient = objectOutputStream;
                //Thread t = new Thread clientThread();
                (clientThreadThreads[i] = clientThread).start();
                players[i] = new Player(clientThread);
                noOfPlayers++;
                System.out.println("game created and client added");
                break;
            }
        }
        informAllPlayers("Game created");
    }

    public void informAllPlayers(String messageToBeBroadcast){
        for(int i = 0; i < noOfPlayers ; i++){
            clientThreadThreads[i].sendObjectToClient(new Message(messageToBeBroadcast));
        }
    }

    public void startGame(){
        System.out.println("start game method");
        System.out.println("no of players "+noOfPlayers);
        message.setText("Game is starting");
        for(int i =0; i < noOfPlayers;i++){
            clientThreadThreads[i].sendObjectToClient(message);
        }
    }

    public void gameMainLogic(){
        //THIS IS WHERE THE SYNCHRONISED CALLS COME IN
        while(true){
            round();
        }
    }

    public void round(){
        message.setText("Enter number: ");
        for(int i =0; i < noOfPlayers;i++){
            clientThreadThreads[i].sendObjectToClient(message);
        }
        for(int i =0; i < noOfPlayers;i++){
            //ClientThread ct;
            Player player;
            object = clientThreadThreads[i].readObjectFromClient();
            player = (Player) object;
            totalScore = totalScore + player.score;
        }
        message.setText("total score is "+totalScore);
        for(int i =0; i < noOfPlayers;i++){
            clientThreadThreads[i].sendObjectToClient(message);
        }
    }


    public void endGame(){

    }

    public void checkPlayersDisconnected(){
        //check if disconnected, if so clean up
    }


}
