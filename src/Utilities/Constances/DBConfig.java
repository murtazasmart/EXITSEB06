package Utilities.Constances;

import java.sql.*;

/**
 * Created by Bhagya Rathnayake on 6/25/2017.
 */
public class DBConfig {

    private static Connection connection;

    public Connection getConnection() {
        Connection connection = null;
        Statement command = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://mysql5018.mywindowshosting.com:3306/db_a278aa_pokerdb", "a278aa_pokerdb", "teamexit123");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

//    public ResultSet executeQuery(String query, Connection connection){
//        ResultSet resultSet = null;
//        return resultSet;
//    }
}
