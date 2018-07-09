package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnUtils {

    public static Connection getMySQLConnection() throws ClassNotFoundException, SQLException{
        String hostName = "localhost";
        String dbName = "shop";
        String userName = "root";
        String password = "фвьшт";

        return getMySqlConnection(hostName, dbName, userName, password);
    }

    public static Connection getMySqlConnection(String hostName, String dbName, String userName, String password)
        throws ClassNotFoundException, SQLException{
        Connection conn = null;
        Class.forName("com.mysql.jdbc.Driver");
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

        conn = DriverManager.getConnection(connectionURL, userName, password);

        return conn;
    }
}
