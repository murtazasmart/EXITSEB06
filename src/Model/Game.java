package Model;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by MA_Laptop on 6/18/2017.
 */
public class Game  extends Thread implements Serializable {
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
            totalScore = totalScore + player.getScore();
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


    //GETTERS AND SETTERS


    public int getScoreTick() {
        return scoreTick;
    }

    public void setScoreTick(int scoreTick) {
        this.scoreTick = scoreTick;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public ClientThread[] getClientThreadThreads() {
        return clientThreadThreads;
    }

    public void setClientThreadThreads(ClientThread[] clientThreadThreads) {
        this.clientThreadThreads = clientThreadThreads;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public int getMaxClientsCount() {
        return maxClientsCount;
    }

    public void setMaxClientsCount(int maxClientsCount) {
        this.maxClientsCount = maxClientsCount;
    }

    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean start) {
        isStart = start;
    }

    public Scanner getScan() {
        return scan;
    }

    public void setScan(Scanner scan) {
        this.scan = scan;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public int getNoOfPlayers() {
        return noOfPlayers;
    }

    public void setNoOfPlayers(int noOfPlayers) {
        this.noOfPlayers = noOfPlayers;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
