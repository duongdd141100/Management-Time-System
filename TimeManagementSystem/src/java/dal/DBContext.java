package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBContext {
    Connection conn;
    PreparedStatement statement;
    ResultSet resultSet;
    
    

    /*USE BELOW METHOD FOR YOUR DATABASE CONNECTION FOR BOTH SINGLE AND MULTILPE SQL SERVER INSTANCE(s)*/
 /*DO NOT EDIT THE BELOW METHOD, YOU MUST USE ONLY THIS ONE FOR YOUR DATABASE CONNECTION*/
    public DBContext() {
        try {
            String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, userID, password);
        } catch (Exception e) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    /*Insert your other code right after this comment*/
 /*Change/update information of your database connection, DO NOT change name of instance variables in this class*/
    private final String serverName = "DESKTOP-MQMEV2R";
    private final String dbName = "TIMEMANAGEMENT";
    private final String portNumber = "1433";
    private final String userID = "sa1";
    private final String password = "123123";

}
