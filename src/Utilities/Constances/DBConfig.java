package Utilities.Constances;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Bhagya Rathnayake on 6/25/2017.
 */
public class DBConfig {

    private static Connection connection;

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/exit_foker", "root", "odt123");
        } catch (Exception e) {
            System.out.println(e);
        }

        return connection;
    }
}
