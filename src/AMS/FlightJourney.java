package AMS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class FlightJourney extends JFrame implements ActionListener
{
    JFrame f;
    JLabel l1, l2, l3;
    JButton btn1, btn2;
    Choice ch1, ch2;

    FlightJourney()
    {
        f = new JFrame("Select Source & Destination");
        f.setBackground(Color.green);
        f.setLayout(null);

        l1 = new JLabel();
        l1.setBounds(0, 0, 500, 270);
        l1.setLayout(null);
        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("AMS/Icons/6.jpg"));
        Image i = img.getImage().getScaledInstance(700, 370, Image.SCALE_SMOOTH);
        ImageIcon img2 = new ImageIcon(i);
        l1.setIcon(img2);

        l2 = new JLabel("Source");
        l2.setVisible(true);
        l2.setBounds(40, 60, 150, 30);
        l2.setForeground(Color.WHITE);
        Font f1 = new Font("Arial", Font.BOLD, 21);
        l2.setFont(f1);
        l1.add(l2);
        f.add(l1);

        l3 = new JLabel("Destination");
        l3.setVisible(true);
        l3.setBounds(40, 120, 150, 30);
        l3.setForeground(Color.WHITE);
        l3.setFont(f1);
        l1.add(l3);

        ch1 = new Choice();
        ch1.setBounds(240, 60, 190, 25);
        try
        {
            ConnectionClass obj = new ConnectionClass();
            String q = "select distinct source from bookedflight";
            ResultSet rest = obj.stm.executeQuery(q);
            while (rest.next())
            {
                ch1.add(rest.getString("source"));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        ch2 = new Choice();
        ch2.setBounds(240, 120, 190, 25);
        try
        {
            ConnectionClass obj = new ConnectionClass();
            String q = "select distinct destination from bookedflight";
            ResultSet rest = obj.stm.executeQuery(q);
            while (rest.next())
            {
                ch2.add(rest.getString("destination"));
            }
            rest.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        l1.add(ch1);
        l1.add(ch2);
        ch1.setFont(f1);
        ch2.setFont(f1);

        btn1 = new JButton("Search");
        btn1.setBounds(140, 185, 100, 30);
        btn1.addActionListener(this);
        l1.add(btn1);

        btn2 = new JButton("Close");
        btn2.setBounds(260, 185, 100, 30);
        btn2.addActionListener(this);
        btn2.setBackground(Color.RED);
        btn2.setForeground(Color.WHITE);
        l1.add(btn2);

        f.setSize(500, 270);
        f.setLocation(450, 250);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == btn2)
        {
            f.setVisible(false);
        }
        if (e.getSource() == btn1)
        {
            f.setVisible(false);
            new FlightJourneyDetails(ch1.getSelectedItem(), ch2.getSelectedItem()).setVisible(true);
        }
    }

    public static void main(String[] args)
    {
        new FlightJourney();
    }
}
