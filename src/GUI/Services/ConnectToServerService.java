package GUI.Services;

import Model.Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by MA_Laptop on 6/21/2017.
 */
public class ConnectToServerService {

    public Client connectToServerLocalServer(){
        try {
            Client client = new Client();
            InetAddress ipAddress = InetAddress.getByName("169.254.51.167");
            Socket clientSocket = new Socket(ipAddress, 4445);
            System.out.println("connected?");
            ObjectOutputStream sendObjectToServer = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream receiveObjectFromServer = new ObjectInputStream(clientSocket.getInputStream());
            System.out.println("streams?");
            client.setIpAddress(ipAddress);
            client.setClientSocket(clientSocket);
            client.setSendObjectToServer(sendObjectToServer);
            client.setReceiveObjectFromServer(receiveObjectFromServer);
            return client;
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host ");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to ");
            e.printStackTrace();
        }
        return null;
    }

    public Client connectToServerGlobalServer() {
        try {
            Client client = new Client();
            InetAddress ipAddress = InetAddress.getByName("169.254.51.167");
            Socket clientSocket = new Socket(ipAddress, 4445);
            System.out.println("connected?");
            ObjectOutputStream sendObjectToServer = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream receiveObjectFromServer = new ObjectInputStream(clientSocket.getInputStream());
            System.out.println("streams?");
            client.setIpAddress(ipAddress);
            client.setClientSocket(clientSocket);
            client.setSendObjectToServer(sendObjectToServer);
            client.setReceiveObjectFromServer(receiveObjectFromServer);
            return client;
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host ");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to ");
            e.printStackTrace();
        }
        return null;
    }
}
