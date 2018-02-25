package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager
{
    public String host = "jdbc:mysql://localhost:3306/";
    public String dbName="mydblab1";
    public String username="root";
    public String password="root";
    public Connection connection;

    public Connection getConnection()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(host + dbName, username, password);
            return connection;
        }
        catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
