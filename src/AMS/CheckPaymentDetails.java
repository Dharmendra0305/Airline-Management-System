package AMS;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class CheckPaymentDetails extends JFrame
{
    JTextField textField;
    JTable table;
    JLabel sector, flightCode, capacity, classCode, className, label;

    CheckPaymentDetails()
    {
        initialize();
    }

    private void initialize()
    {
        setTitle("Payment Details");
        getContentPane().setBackground(Color.WHITE);
        setSize(860, 486);
        setLayout(null);

        sector = new JLabel("Check Payment Details");
        sector.setForeground(Color.BLUE);
        sector.setFont(new Font("Arial", Font.BOLD, 33));
        sector.setBounds(291, 17, 800, 39);
        add(sector);

        JLabel fCode = new JLabel("Username");
        fCode.setFont(new Font("Arial", Font.BOLD, 16));
        fCode.setBounds(190, 160, 150, 26);
        add(fCode);

        textField = new JTextField();
        textField.setBounds(300, 160, 150, 26);
        textField.setFont(new Font("Arial", Font.BOLD, 14));
        add(textField);

        JButton show = new JButton("Show");
        show.setFont(new Font("Arial", Font.BOLD, 14));
        show.setBackground(Color.BLACK);
        show.setForeground(Color.WHITE);
        show.setBounds(500, 160, 150, 26);
        add(show);

        table = new JTable();
        table.setBounds(93, 297, 766, 87);
        add(table);

        flightCode = new JLabel("Ticket ID");
        flightCode.setFont(new Font("Arial", Font.BOLD, 14));
        flightCode.setBounds(117, 262, 108, 26);
        add(flightCode);

        capacity = new JLabel("Price");
        capacity.setFont(new Font("Arial", Font.BOLD, 14));
        capacity.setBounds(237, 268, 388, 14);
        add(capacity);

        classCode = new JLabel("Journey Date");
        classCode.setFont(new Font("Arial", Font.BOLD, 14));
        classCode.setBounds(362, 264, 101, 24);
        add(classCode);

        className = new JLabel("Journey Time");
        className.setFont(new Font("Arial", Font.BOLD, 14));
        className.setBounds(485, 268, 114, 14);
        add(className);

        JLabel cardNo = new JLabel("Username");
        cardNo.setFont(new Font("Arial", Font.BOLD, 14));
        cardNo.setBounds(620, 269, 101, 19);
        add(cardNo);

        JLabel phoneNo = new JLabel("Status");
        phoneNo.setFont(new Font("Arial", Font.BOLD, 14));
        phoneNo.setBounds(752, 264, 86, 24);
        add(phoneNo);

        label = new JLabel("");
        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("AMS/Icons/8.jpg"));
        Image i1 = img.getImage().getScaledInstance(1550, 800, Image.SCALE_SMOOTH);
        ImageIcon img2 = new ImageIcon(i1);
        label.setIcon(img2);
        label.setBounds(0, 0, 960, 590);
        add(label);
        setVisible(true);

        show.addActionListener(_ -> {
            try
            {
                String usn = textField.getText();
                ConnectionClass obj = new ConnectionClass();
                String str = "select tid, price, journey_date, journey_time, username, status from bookedFlight where username='"+usn+"' and status='Success'";
                ResultSet r = obj.stm.executeQuery(str);
                table.setModel(DbUtils.resultSetToTableModel(r));
                table.setFont(new Font("Arial", Font.BOLD, 14));
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        });
        setSize(960, 590);
        setLocation(40, 20);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new CheckPaymentDetails();
    }
}


