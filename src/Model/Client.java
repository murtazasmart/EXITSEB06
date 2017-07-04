package Model;

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
    int score, gameID, numberOfPlayers;
    String gameName;
    Scanner scan;
    boolean isConnected;
    Player player;
    int playerId;

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