package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager
{
    public String host = "jdbc:mysql://localhost:3306/";
    public String dbName="mydblab1?autoReconnect=true&&useSSL=false";
    public String username="root";
    public String password="root";
    public Connection connection=null;

    public Connection getConnection()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(host + dbName, username, password);
            System.out.println("Connection succesfull");
        }
        catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
            System.out.println("Connection error!");
        }
        return connection;
    }
}
