package AMS;

import java.sql.*;

public class ConnectionClass 
{
    Connection con;
    Statement stm;

    public ConnectionClass() 
    {
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ams", "root", "Dev@03#06");
            stm = con.createStatement();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        new ConnectionClass();
    }
}
