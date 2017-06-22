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
    String gameName, gameCreatorName;
    int gameID, totalScore;
    ClientThread[] clientThreadThreads;
    Player[] players;
    int maxClientsCount = 10;
    boolean isStart = false;
    Scanner scan;
    Message message;
    int noOfPlayers;
    Object object;
    Referee referee;

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
        referee = new Referee();
    }

    @Override
    public void run(){
        startGame();
        while (true){
            round();
            if(noOfPlayers == 1)
                break;
        }
        System.out.println("Game has ended");

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
                players[i].setPlayerId(i);;
                noOfPlayers++;
                System.out.println("here1");
                clientThreadThreads[i].sendMessageObjectToClient(new Message("uve been added"));
                break;
            }
        }
        //informAllPlayers("player added");
        for(int i = 0; i < noOfPlayers ; i++){
            clientThreadThreads[i].sendMessageObjectToClient(new Message("player added"));
        }
    }

    public void addPlayer(Socket clientSocket, ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream, Game clientGameObject) {
        for(int i = 0; i < maxClientsCount; i++){
            if(clientThreadThreads[i]==null){
                ClientThread clientThread = new ClientThread();
                clientThread.clientSocket = clientSocket;
                clientThread.receiveObjectFromClient = objectInputStream;
                clientThread.sendObjectToClient = objectOutputStream;
                //Thread t = new Thread clientThread();
                (clientThreadThreads[i] = clientThread).start();
                players[i] = new Player(clientThread);
                players[i].setUsername(clientGameObject.getGameCreatorName());
                players[i].setPlayerId(i);
                players[i].setNumberofplayers(maxClientsCount);
                noOfPlayers++;
                System.out.println("game created and client added");
                break;
            }
        }
        if(noOfPlayers == players[0].getNumberofplayers())
            System.out.println("No of players in game object and player object same");
        informAllPlayers("Game created");
    }

    public void informAllPlayers(String messageToBeBroadcast){
        for(int i = 0; i < noOfPlayers ; i++){
            clientThreadThreads[i].sendMessageObjectToClient(new Message(messageToBeBroadcast));
        }
    }

    public void informAllPlayers(Player[] players){
        for(int i = 0; i < noOfPlayers ; i++){
            System.out.println("thread sent to no "+i);
            clientThreadThreads[i].sendPlayerObjectToClient(players[i]);
        }
    }

    public void startGame(){
        System.out.println("start game method");
        System.out.println("no of players "+noOfPlayers);
        message.setText("Game is starting");
        for(int i =0; i < noOfPlayers;i++){
            clientThreadThreads[i].sendMessageObjectToClient(message);
        }
    }

    public Player[] handleKickedPlayers(Player[] players){
        int currentNoOfPlayers = noOfPlayers;
        String[] allUsernames = players[0].getAllUsernames();
        String[] newAllUsernames = new String[(currentNoOfPlayers-1)];
        Player[] newPlayers = new Player[(currentNoOfPlayers-1)];
        int j = 0;
        for(int i = 0;i<noOfPlayers;i++){
            if(players[i].isKicked() == true ){
                continue;
            }
            newPlayers[j] = players[i];
            newAllUsernames[j] = players[i].getUsername();
            newPlayers[j].setNumberofplayers(currentNoOfPlayers-1);
            j++;
        }
        for(int i = 0;i<currentNoOfPlayers-1;i++){
            newPlayers[i].setAllUsernames(newAllUsernames);
        }
        return newPlayers;
    }

//    public void gameMainLogic(){
//        //THIS IS WHERE THE SYNCHRONISED CALLS COME IN
//        while(true){
//            round();
//        }
//    }

    public int getKickedPlayerId(Player[] players){
        for(int i =0;i<players.length;i++){
            if(players[i].isKicked()==true)
                return i;
        }
        return 0;
    }

    public void round(){
        System.out.println("before start game");
        players = referee.startGame(players);
        System.out.println("players about to be sent");
        informAllPlayers(players);
        System.out.println("players sent");

        //HANDLE ERROR HERE WHEN CLIENT EXITS SUDDENLY, EACH OBJECT IS WAITED FOR ONE BY ONE IN ORDER OF CLIENTHTREADS
        for(int i =0; i < noOfPlayers;i++){
            players[i] = (Player)clientThreadThreads[i].readObjectFromClient();
        }

        players = referee.CardExchange(players);

        players = referee.calculateOverallScore(players);

        players = referee.updatePlayerToBeKicked(players);

        informAllPlayers(players);

        for(int i =0; i < noOfPlayers;i++){
            players[i] = (Player)clientThreadThreads[i].readObjectFromClient();
        }

        if(noOfPlayers > 2) {
            players = referee.updatePointsDonation(players);
        }

        //SEND MESSAGE TO TELL WHO GOT DONATED POINTS
        informAllPlayers(players);

        informAllPlayers(players[getKickedPlayerId(players)].getUsername()+" was kicked and he donated 1/4 of his points to "+players[players[getKickedPlayerId(players)].getPlayerIdToDonatePoints()].getUsername());

        System.out.println("kicked player id "+getKickedPlayerId(players));

        clientThreadThreads = ClientThread.removeClientThread(getKickedPlayerId(players),clientThreadThreads);

        players = handleKickedPlayers(players);

        noOfPlayers--;

        System.out.println("round ended, now noOfPlayers is "+noOfPlayers);

        // REMOVE PLAYER referee


//        message.setText("Enter number: ");
//        for(int i =0; i < noOfPlayers;i++){
//            clientThreadThreads[i].sendMessageObjectToClient(message);
//        }
//        for(int i =0; i < noOfPlayers;i++){
//            //ClientThread ct;
//            Player player;
//            object = clientThreadThreads[i].readObjectFromClient();
//            player = (Player) object;
//            totalScore = totalScore + player.getScore();
//        }
//        message.setText("total score is "+totalScore);
//        for(int i =0; i < noOfPlayers;i++){
//            clientThreadThreads[i].sendMessageObjectToClient(message);
//        }
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

    public String getGameCreatorName() {
        return gameCreatorName;
    }

    public void setGameCreatorName(String gameCreatorName) {
        this.gameCreatorName = gameCreatorName;
    }
}
