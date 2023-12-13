package db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
 
public class DBHelper {
    private static Connection connection = null;
    private static final Properties prop = null;
    private static final Logger logger = Logger.getLogger(DBHelper.class.getName());

    public static String getProperty(String key) {
        if (prop != null) {
            return prop.getProperty(key);
        } else {
            Properties temp = new Properties();
            InputStream inputStream = DBHelper.class.getClassLoader().getResourceAsStream("db/database.properties");
            try {
                temp.load(inputStream);
                return temp.getProperty(key);
            } catch (IOException ex) {
                logger.log(Level.SEVERE, ex.getMessage(), ex);
                return null;
            }
        }
    }

    public static Connection getConnection() {
        if (connection != null)
            return connection;
        else {
            try {
                String driver = getProperty("driver");
                String url = getProperty("url");
                String user = getProperty("user");
                String password = getProperty("password");
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException | SQLException ex) {
                logger.log(Level.SEVERE, ex.getMessage(), ex);
            } 
            return connection;
        }
    }
}