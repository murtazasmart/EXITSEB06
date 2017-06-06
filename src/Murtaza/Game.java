package Murtaza;

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

    public Game(String gameName, int gameID) {
        this.gameName = gameName;
        this.gameID = gameID;
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

    public void addPlayer(ClientThread clientThread, Socket clientSocket){
        for(int i = 0; i < maxClientsCount; i++){
            if(clientThreadThreads[i]==null){
                //ClientThread object is sent to update the
                clientThread.clientSocket = clientSocket;
                //Thread t = new Thread clientThread();
                (clientThreadThreads[i] = clientThread).start();
                players[i] = new Player(clientThread);
                noOfPlayers++;
                System.out.println("here1");
                break;
            }
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
            ClientThread ct;
            object = clientThreadThreads[i].readObjectFromClient();
            ct = (ClientThread)object;
            totalScore = totalScore + ct.score;
        }
        message.setText("total score is "+totalScore);
        for(int i =0; i < noOfPlayers;i++){
            clientThreadThreads[i].sendObjectToClient(message);
        }
    }


    public void endGame(){

    }

}
