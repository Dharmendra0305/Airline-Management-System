package AMS;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class ViewPassenger extends JFrame
{
    JTable t;
    String[] x = {"Username", "Name", "Age", "Date Of Birth", "Address", "Phone", "Email", "Nationality", "Gender", "Passport"};
    String[][] y = new String[20][10];
    int i = 0, j = 0;
    Font f;

    ViewPassenger()
    {
        super("All Passenger Details");
        setSize(1300, 400);
        setLocation(0, 10);
        f = new Font("Arial", Font.BOLD, 16);

        try
        {
            ConnectionClass obj = new ConnectionClass();
            String q = "Select * from passenger";
            ResultSet rest = obj.stm.executeQuery(q);
            while (rest.next())
            {
                y[i][j++] = rest.getString("username");
                y[i][j++] = rest.getString("name");
                y[i][j++] = rest.getString("age");
                y[i][j++] = rest.getString("dob");
                y[i][j++] = rest.getString("address");
                y[i][j++] = rest.getString("phone");
                y[i][j++] = rest.getString("email");
                y[i][j++] = rest.getString("nationality");
                y[i][j++] = rest.getString("gender");
                y[i][j++] = rest.getString("passport");
                i++;
                j = 0;
            }
            t = new JTable(y, x);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        t.setFont(f);
        t.setBackground(Color.BLACK);
        t.setForeground(Color.WHITE);
        JScrollPane js = new JScrollPane(t);
        add(js);
    }

    public static void main(String[] args)
    {
        new ViewPassenger().setVisible(true);
    }
}
