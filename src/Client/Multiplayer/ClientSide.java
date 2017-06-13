package Client.Multiplayer;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by Bhagya Rathnayake on 6/5/2017.
 */
public class ClientSide implements Runnable{

    private static Socket clientSocket=null;
    private static DataInputStream dataInputStream=null;
    private static BufferedReader bufferedReader=null;
    private static PrintStream printStream=null;
    private static boolean isClosed=false;

    public ClientSide()
    {

    }

    public void method1()
    {
        int port =2222;
        String host ="127.0.0.1";

        try{
            clientSocket = new Socket(host,port);
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            printStream = new PrintStream(clientSocket.getOutputStream());
            dataInputStream = new DataInputStream(clientSocket.getInputStream());
            if(clientSocket!=null && printStream!=null && dataInputStream!=null)
            {
                try{
                    new Thread(new ClientSide()).start();
                    while(!isClosed)
                    {
                        printStream.println(bufferedReader.readLine().trim());
                    }
                    printStream.close();
                    dataInputStream.close();
                    clientSocket.close();
                }

                catch(Exception e)
                {
                    System.out.println(e);
                }
            }
        }

        catch(Exception e)
        {
            System.out.println("CLIENT SIDE EXCEPTION RAISED "+e);
        }
    }

    @Override
    public void run() {
        String responseLine;

        try{
            while((responseLine=dataInputStream.readLine()) != null)
            {
                System.out.println(responseLine);
            }

            isClosed=true;
        }

        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
