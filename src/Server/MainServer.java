package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by MA_Laptop on 6/18/2017.
 */
public class MainServer {
    public static void main(String[] args) {
        //only for testing
        Server server = new Server("169.254.51.167",4445);
        server.start();
    }
}
