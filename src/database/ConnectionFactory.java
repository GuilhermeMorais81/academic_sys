package database;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionFactory {
    private static String user;
    private static String passwd;
    private static String host;
    private static String port;
    private static String database;

    public static void setConfiguration() throws Exception {
        Properties properties = new Properties();
        try(FileInputStream input = new FileInputStream("config.properties")) {
            properties.load(input);
            user = properties.getProperty("USER");
            passwd = properties.getProperty("PASSWORD");
            host = properties.getProperty("HOST");
            port = properties.getProperty("PORT");
            database = properties.getProperty("DATABASE");
        }
    }

    public static Connection getConnection() {
        try {
            setConfiguration();
            var conString = String.format("jdbc:postgresql://%s:%s/%s", host, port, database);
            Connection connection = DriverManager.getConnection(conString, user, passwd);
            return connection;
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}