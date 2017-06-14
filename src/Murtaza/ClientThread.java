package Murtaza;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by MA_Laptop on 5/28/2017.
 */
public class ClientThread extends Thread implements Serializable{

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
            object = (Object)receiveObjectFromClient.readObject();
            return  object;
        } catch (IOException |ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
