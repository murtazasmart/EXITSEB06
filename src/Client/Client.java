package Client;

import Model.*;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.StringJoiner;

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
    int score, gameID, numberOfPlayers;
    String gameName;
    Scanner scan;
    boolean isConnected;
    Player player;
    int playerId;

    public static void main(String[] args) {
        Client client = new Client();
        client.connectToServer();
        client.joinGame();
    }

    public boolean connectToServer(){
        try {
            System.out.println("Enter IP of host followed:");
            scan = new Scanner(System.in);
            ipAddress = InetAddress.getByName("169.254.51.167");
            clientSocket = new Socket(ipAddress, 4445);
            System.out.println("connected?");
            sendObjectToServer = new ObjectOutputStream(clientSocket.getOutputStream());
            receiveObjectFromServer = new ObjectInputStream(clientSocket.getInputStream());
            System.out.println("streams?");
            return true;
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host ");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to ");
            System.exit(1);
        }
        return false;
    }

    public void joinGame(){
        try {
            Scanner scan = new Scanner(System.in);
            player = new Player();
            System.out.println("Enter player name:");
            username = scan.next();
            player.setUsername(username);
            System.out.println("do u wanna join or create game(c/j)");
            String res = scan.next();
            if(res.equalsIgnoreCase("c")){
                Game game = new Game();
                System.out.println("Enter game name");
                game.setGameName(scan.next());
                System.out.println("Enter number of players");
                game.setMaxClientsCount(scan.nextInt());
                game.setGameCreatorName(player.getUsername());
                //game.gameName = "garena";
                sendObjectToServer.writeObject(game);
                sendObjectToServer.flush();
            }
            else if(res.equalsIgnoreCase("j")){
                ClientThread clientThread = new ClientThread();
                System.out.println("Enter game name");
                clientThread.setGameName(scan.next());
                clientThread.setUsername(player.getUsername());
                //ct.gameName="garena";
                sendObjectToServer.writeObject(clientThread);
                sendObjectToServer.flush();
            }
//            player = new Player();
//            System.out.println("Enter player name:");
//            player.setUsername(scan.next());
            Message message = (Message)receiveObjectFromServer.readObject();
            System.out.println(" Messag befor while loop :" +message.getText());
            while(true){
                message = (Message)receiveObjectFromServer.readObject();
                System.out.println("messgae inside while loop " +message.getText());
                if(message.getText().equalsIgnoreCase("Game is starting"))
                    break;
            }
            System.out.println("ive broken out!!");
            while(true){
                System.out.println("started in loop");
                player = (Player)receiveObjectFromServer.readObject();
                System.out.println("received player boject from client");

                for(int i = 0;i<player.getNumberofplayers();i++){
                    System.out.println(player.getAllUsernames()[i]);
                }

                String[][] playersCards = player.getCardHand();
                for(int i = 0 ; i<playersCards.length;i++){
                    if(player.getAllUsernames()[i].equalsIgnoreCase(username)){
                        for(int j = 0 ; j < 5 ; j++){
                            System.out.print(playersCards[i][j]+" ");
                        }
                        System.out.println("player "+player.getUsername()+" score is "+player.getScore());
                    }else{
                            System.out.println("Player "+player.getAllUsernames()[i]+" 2 inital cards are "+player.getIndividualCardHand(i,0)+" "+player.getIndividualCardHand(i,1));

                    }

                }

                //SWAP OPTION PUT HERE
                System.out.println("Enter swap options from 1 to 5");
                for(int i =0 ; i<5;i++){
                    System.out.println("Do u wanna swap the "+(i+1)+" card?(y/n)");
                    res = scan.next();
                    if(res.equalsIgnoreCase("y")){
                        player.getSwapCards()[i] = true;
                    }
                }

                sendObjectToServer.writeObject(player);

                player = (Player)receiveObjectFromServer.readObject();
                playersCards = player.getCardHand();
                for(int i = 0 ; i<playersCards.length;i++){
                    if(player.getUsername().equalsIgnoreCase(username)){
                        for(int j = 0 ; j < 5 ; j++){
                            System.out.print(playersCards[i][j]+" ");
                        }
                        System.out.println("player "+player.getUsername()+" score is "+player.getScore());
                    }
                }

                if(player.isKicked() == true){
                    System.out.println("You've been kicked");
                    if(player.getAllUsernames().length >1){
                        for(int i =0;i<player.getAllUsernames().length;i++){
                            System.out.print(player.getAllUsernames()[i]+" ");
                        }
                        //HANDLE IF WRONG PLAYER NAME INCLUDED
                        if(player.getNumberofplayers() > 2){
                            System.out.println("Choose a person to donate quarter of your points");
                            res = scan.next();
                            for(int i =0;i<player.getAllUsernames().length;i++){
                                if(player.getAllUsernames()[i].equalsIgnoreCase(res))
                                    player.setPlayerIdToDonatePoints(i);
                            }
                        }
                    }
                }

                sendObjectToServer.writeObject(player);

                player = (Player)receiveObjectFromServer.readObject();

                for(int i = 0;i<player.getNumberofplayers();i++){
                    System.out.println(player.getAllUsernames()[i]+" score is "+player.getAllScores()[i]);
                }

                message = (Message)receiveObjectFromServer.readObject();

                if(player.getNumberofplayers() > 2){
                    System.out.println(message.getText());
                }



                if(player.isKicked() == true || player.getNumberofplayers() == 2){
                    break;
                }

                System.out.println("player kicked checked");

                //RECEIVE MESSAGE OBJECT TO SEE WHO GOT DONATED POINTS

//                message = (Message)receiveObjectFromServer.readObject();
//                System.out.println(message.getText());
//                Player player = new Player();
//                player.setScore(scan.nextInt());
//                sendObjectToServer.reset();
//                sendObjectToServer.writeObject(player);
//                sendObjectToServer.flush();
//                message = (Message)receiveObjectFromServer.readObject();
//                System.out.println(message.getText());
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

    public String getCardType(String str){
        String type=str.substring(0,str.indexOf("-"));
        return type;
    }

    public String getCardValue(String str){
        String value = str.substring(str.indexOf("-")+1);
        return value;
    }

    public String getCardFolderName(String cardHand) {
        String folderName,res;
        res = getCardType(cardHand);
        if(res.equalsIgnoreCase("H"))
            folderName = "Hearts";
        else if(res.equalsIgnoreCase("D"))
            folderName = "Diamonds";
        else if(res.equalsIgnoreCase("S"))
            folderName = "Spades";
        else
            folderName = "Clubs";

        return folderName;
    }

    public String getCardFileName(String cardHand) {
        String fileName, res;
        res = getCardType(cardHand);
        if(res.equalsIgnoreCase("H"))
            fileName = "H";
        else if(res.equalsIgnoreCase("D"))
            fileName = "D";
        else if(res.equalsIgnoreCase("S"))
            fileName = "S";
        else
            fileName = "C";
        res = getCardValue(cardHand);
        fileName = fileName+res;
        return fileName;
    }

    //getValueCard
    /*
    public boolean sendObjectToServer(){

    }
*/

    public Client(){

    }

    public Client(Client client) {
        this.clientSocket = client.clientSocket;
        this.writeToServer = client.writeToServer;
        this.readFromServer = client.readFromServer;
        this.sendObjectToServer = client.sendObjectToServer;
        this.receiveObjectFromServer = client.receiveObjectFromServer;
        this.ipAddress = client.ipAddress;
        this.username = client.username;
        this.score = client.score;
        this.gameID = client.gameID;
        this.numberOfPlayers = client.numberOfPlayers;
        this.gameName = client.gameName;
        this.scan = client.scan;
        this.isConnected = client.isConnected;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public PrintWriter getWriteToServer() {
        return writeToServer;
    }

    public void setWriteToServer(PrintWriter writeToServer) {
        this.writeToServer = writeToServer;
    }

    public BufferedReader getReadFromServer() {
        return readFromServer;
    }

    public void setReadFromServer(BufferedReader readFromServer) {
        this.readFromServer = readFromServer;
    }

    public ObjectOutputStream getSendObjectToServer() {
        return sendObjectToServer;
    }

    public void setSendObjectToServer(ObjectOutputStream sendObjectToServer) {
        this.sendObjectToServer = sendObjectToServer;
    }

    public ObjectInputStream getReceiveObjectFromServer() {
        return receiveObjectFromServer;
    }

    public void setReceiveObjectFromServer(ObjectInputStream receiveObjectFromServer) {
        this.receiveObjectFromServer = receiveObjectFromServer;
    }

    public InetAddress getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(InetAddress ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

}