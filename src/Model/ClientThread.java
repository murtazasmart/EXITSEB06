package Model;

import Murtaza.*;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by MA_Laptop on 6/18/2017.
 */
public class ClientThread extends Thread implements Serializable {

    Socket clientSocket;
    PrintWriter writeToServer;
    BufferedReader readFromServer;
    ObjectOutputStream sendObjectToClient;
    ObjectInputStream receiveObjectFromClient;
    InetAddress ipAddress;
    String username;
    int score, gameID;
    String gameName;
    Object object;
    boolean isDisconnected;

    public ClientThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
        try {
            receiveObjectFromClient = new ObjectInputStream(clientSocket.getInputStream());
            sendObjectToClient = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ClientThread(){

    }

    @Override
    public void run() {

    }

    public boolean sendObjectToClient(Message message){
        try {
            sendObjectToClient.reset();
            sendObjectToClient.writeObject(message);
            sendObjectToClient.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Object readObjectFromClient() {
        try {
            object = receiveObjectFromClient.readObject();
            return  object;
        } catch (IOException |ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }




    //GETTERS SETTERS

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

    public ObjectOutputStream getSendObjectToClient() {
        return sendObjectToClient;
    }

    public void setSendObjectToClient(ObjectOutputStream sendObjectToClient) {
        this.sendObjectToClient = sendObjectToClient;
    }

    public ObjectInputStream getReceiveObjectFromClient() {
        return receiveObjectFromClient;
    }

    public void setReceiveObjectFromClient(ObjectInputStream receiveObjectFromClient) {
        this.receiveObjectFromClient = receiveObjectFromClient;
    }

    public InetAddress getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(InetAddress ipAddress) {
        this.ipAddress = ipAddress;
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

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public boolean isDisconnected() {
        return isDisconnected;
    }

    public void setDisconnected(boolean disconnected) {
        isDisconnected = disconnected;
    }
}
