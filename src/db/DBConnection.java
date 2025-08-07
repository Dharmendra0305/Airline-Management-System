package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection
{
    private static final String URL = "jdbc:mysql://localhost:3306/airline_booking";
    private static final String USER = "root";
    private static final String PASSWORD = "Dev@03#06";

    public static Connection getConnection()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("MYSQL JDBC Driver not found.");
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            System.out.println("Connection to database failed.");
            e.printStackTrace();
        }
        return null;
    }
}
