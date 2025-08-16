package AMS;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame implements ActionListener
{
    JLabel l1;
    Font f, f1, f2;

    HomePage()
    {
        super("Airlines India Home Page");
        setLocation(0, 0);
        setSize(1550, 800);

        f = new Font("Lucida Fax", Font.BOLD, 20);
        f1 = new Font("MS UI Gothic", Font.BOLD, 18);
        f2 = new Font("Gadugi", Font.BOLD, 35);

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("AMS/Icons/4.jpg"));
        Image i = img.getImage().getScaledInstance(1550, 800, Image.SCALE_SMOOTH);
        ImageIcon img2 = new ImageIcon(i);
        l1 = new JLabel(img2);

        JMenuBar m1 = new JMenuBar();
        EtchedBorder mBorder = new EtchedBorder(EtchedBorder.RAISED, Color.GRAY, Color.BLACK);

        JMenu men1 = new JMenu("Passenger Profile");
        JMenuItem ment1 = new JMenuItem("Add Passenger Profile");
        JMenuItem ment2 = new JMenuItem("View Passenger Profile");
        men1.add(ment1);
        men1.add(ment2);
        men1.setBorder(mBorder);
        m1.add(Box.createHorizontalStrut(10));
        m1.add(men1);

        JMenu men2 = new JMenu("Manage Passenger");
        JMenuItem ment3 = new JMenuItem("Update Passenger Details");
        men2.add(ment3);
        men2.setBorder(mBorder);
        m1.add(Box.createHorizontalStrut(10));
        m1.add(men2);

        JMenu men3 = new JMenu("Your Flight");
        JMenuItem ment4 = new JMenuItem("Book Flight");
        JMenuItem ment5 = new JMenuItem("View Booked Flight");
        men3.add(ment4);
        men3.add(ment5);
        men3.setBorder(mBorder);
        m1.add(Box.createHorizontalStrut(10));
        m1.add(men3);

        JMenu men4 = new JMenu("Flight Details");
        JMenuItem ment6 = new JMenuItem("Journey Details");
        JMenuItem ment7 = new JMenuItem("Flight Zone");
        men4.add(ment6);
        men4.add(ment7);
        men4.setBorder(mBorder);
        m1.add(Box.createHorizontalStrut(10));
        m1.add(men4);

        JMenu men5 = new JMenu("Cancellation");
        JMenuItem ment8 = new JMenuItem("Cancel Ticket");
        JMenuItem ment9 = new JMenuItem("View Cancelled Ticket");
        men5.add(ment8);
        men5.add(ment9);
        men5.setBorder(mBorder);
        m1.add(Box.createHorizontalStrut(10));
        m1.add(men5);

        JMenu men6 = new JMenu("Bill");
        JMenuItem ment10 = new JMenuItem("Check Payment");
        men6.add(ment10);
        men6.setBorder(mBorder);
        m1.add(Box.createHorizontalStrut(10));
        m1.add(men6);

        JMenu men7 = new JMenu("Logout");
        JMenuItem ment11 = new JMenuItem("Exit");
        men7.add(ment11);
        men7.setBorder(mBorder);
        m1.add(Box.createHorizontalStrut(10));
        m1.add(men7);

        m1.setBackground(new java.awt.Color(4, 1, 54));

        men1.setFont(f);
        men2.setFont(f);
        men3.setFont(f);
        men4.setFont(f);
        men5.setFont(f);
        men6.setFont(f);
        men7.setFont(f);

        men1.setForeground(Color.GRAY);
        men2.setForeground(Color.GRAY);
        men3.setForeground(Color.GRAY);
        men4.setForeground(Color.GRAY);
        men5.setForeground(Color.GRAY);
        men6.setForeground(Color.GRAY);
        men7.setForeground(Color.RED);

        ment1.setFont(f1);
        ment2.setFont(f1);
        ment3.setFont(f1);
        ment4.setFont(f1);
        ment5.setFont(f1);
        ment6.setFont(f1);
        ment7.setFont(f1);
        ment8.setFont(f1);
        ment9.setFont(f1);
        ment10.setFont(f1);
        ment11.setFont(f1);

        ment1.setBackground(Color.BLACK);
        ment2.setBackground(Color.BLACK);
        ment3.setBackground(Color.BLACK);
        ment4.setBackground(Color.BLACK);
        ment5.setBackground(Color.BLACK);
        ment6.setBackground(Color.BLACK);
        ment7.setBackground(Color.BLACK);
        ment8.setBackground(Color.BLACK);
        ment9.setBackground(Color.BLACK);
        ment10.setBackground(Color.BLACK);
        ment11.setBackground(Color.BLACK);

        ment1.setForeground(Color.YELLOW);
        ment2.setForeground(Color.YELLOW);
        ment3.setForeground(Color.YELLOW);
        ment4.setForeground(Color.YELLOW);
        ment5.setForeground(Color.YELLOW);
        ment6.setForeground(Color.YELLOW);
        ment7.setForeground(Color.YELLOW);
        ment8.setForeground(Color.YELLOW);
        ment9.setForeground(Color.YELLOW);
        ment10.setForeground(Color.YELLOW);
        ment11.setForeground(Color.RED);

        ment1.addActionListener(this);
        ment2.addActionListener(this);
        ment3.addActionListener(this);
        ment4.addActionListener(this);
        ment5.addActionListener(this);
        ment6.addActionListener(this);
        ment7.addActionListener(this);
        ment8.addActionListener(this);
        ment9.addActionListener(this);
        ment10.addActionListener(this);
        ment11.addActionListener(this);

        setJMenuBar(m1);
        add(l1);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
       String cmd = e.getActionCommand();
        switch (cmd)
        {
            case "Add Passenger Profile" -> new AddPassengerDetails();
            case "View Passenger Profile" -> new ViewPassenger().setVisible(true);
            case "Update Passenger Details" -> new UpdatePassenger().setVisible(true);
            case "Book Flight" -> new BookFlight().setVisible(true);
            case "View Booked Flight" -> new ViewBookedFlight().setVisible(true);
            case "Journey Details" -> new FlightJourney();
            case "Flight Zone" -> new FlightZone().setVisible(true);
            case "Cancel Ticket" -> new CancelFlight().setVisible(true);
            case "View Cancelled Ticket" -> new ViewCancelledTicket().setVisible(true);
            case "Check Payment" -> new CheckPaymentDetails().setVisible(true);
            case "Exit" -> System.exit(0);
        }
    }

    public static void main(String[] args)
    {
        new HomePage().setVisible(true);
    }
}
