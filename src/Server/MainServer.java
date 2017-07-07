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
        //only for testingtt
        Server server = new Server("169.254.51.167",4445);
        server.start();
//        Connection connection;
//        Statement command = null;
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            connection = DriverManager.getConnection("jdbc:mysql://mysql5018.mywindowshosting.com:3306/db_a278aa_pokerdb", "a278aa_pokerdb", "teamexit123");
//            command = connection.createStatement();
//            command.execute("INSERT INTO highscore(`PlayerName`,`Score`) VALUES ('Shan','123')");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

//        HostController hostController = new HostController();
//        hostController.startView();
    }
}
