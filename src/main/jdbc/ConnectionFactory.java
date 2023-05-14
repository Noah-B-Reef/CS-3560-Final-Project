package src.main.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = "jdbc:postgresql://localhost:5432/library_db";
    //change USER and PWD values to your respective usernames and passwords
    private static final String USER = "nreef";

<<<<<<< HEAD
    private static final String PWD = "123";
=======
    private static final String PWD = "1234";
>>>>>>> 115eebad81f33a5ac6a3c01095f014668ebac00d

    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(ConnectionFactory.URL, ConnectionFactory.USER, ConnectionFactory.PWD);

    }

}