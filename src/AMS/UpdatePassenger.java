package AMS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

public class UpdatePassenger extends JFrame implements ActionListener
{
    Font f, f1;
    Choice ch;
    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12;
    JButton btn1, btn2;
    JTextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9;
    JPanel p1, p2, p3;

    UpdatePassenger()
    {
        super("Update Passenger");
        setLocation(450, 10);
        setSize(740, 600);

        f = new Font("Arial", Font.BOLD, 25);
        f1 = new Font("Arial", Font.BOLD, 18);

        ch = new Choice();

        try
        {
            ConnectionClass obj = new ConnectionClass();
            String q = "select username from passenger";
            ResultSet rest = obj.stm.executeQuery(q);
            while (rest.next())
            {
                ch.add(rest.getString("username"));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        l1 = new JLabel("Update Passenger Details");
        l2 = new JLabel("Username");
        l3 = new JLabel("Name");
        l4 = new JLabel("Age");
        l5 = new JLabel("Date Of Birth");
        l6 = new JLabel("Address");
        l7 = new JLabel("Phone");
        l8 = new JLabel("Email");
        l9 = new JLabel("Nationality");
        l10 = new JLabel("Gender");
        l11 = new JLabel("Passport");

        l1.setFont(f);
        l2.setFont(f1);
        l3.setFont(f1);
        l4.setFont(f1);
        l5.setFont(f1);
        l6.setFont(f1);
        l7.setFont(f1);
        l8.setFont(f1);
        l9.setFont(f1);
        l10.setFont(f1);
        l11.setFont(f1);
        ch.setFont(f1);

        tf1 = new JTextField();
        tf2 = new JTextField();
        tf3 = new JTextField();
        tf4 = new JTextField();
        tf5 = new JTextField();
        tf6 = new JTextField();
        tf7 = new JTextField();
        tf8 = new JTextField();
        tf9 = new JTextField();

        tf1.setFont(f1);
        tf2.setFont(f1);
        tf3.setFont(f1);
        tf4.setFont(f1);
        tf5.setFont(f1);
        tf6.setFont(f1);
        tf7.setFont(f1);
        tf8.setFont(f1);
        tf9.setFont(f1);

        btn1 = new JButton("Update Passenger");
        btn2 = new JButton("Back");

        btn1.setFont(f1);
        btn2.setFont(f1);

        btn1.setBackground(Color.BLUE);
        btn2.setBackground(Color.RED);

        btn1.setForeground(Color.WHITE);
        btn2.setForeground(Color.WHITE);

        l1.setHorizontalAlignment(JLabel.CENTER);

        btn1.addActionListener(this);
        btn2.addActionListener(this);

        p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 1, 10, 10));
        p1.add(l1);

        p2 = new JPanel();
        p2.setLayout(new GridLayout(11, 2, 10, 10));
        p2.add(l2);
        p2.add(ch);
        p2.add(l3);
        p2.add(tf1);
        p2.add(l4);
        p2.add(tf2);
        p2.add(l5);
        p2.add(tf3);
        p2.add(l6);
        p2.add(tf4);
        p2.add(l7);
        p2.add(tf5);
        p2.add(l8);
        p2.add(tf6);
        p2.add(l9);
        p2.add(tf7);
        p2.add(l10);
        p2.add(tf8);
        p2.add(l11);
        p2.add(tf9);
        p2.add(btn1);
        p2.add(btn2);

        p3 = new JPanel();
        p3.setLayout(new GridLayout(1, 1, 10, 10));
        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("AMS/Icons/5.jpg"));
        Image i = img.getImage().getScaledInstance(300, 500, Image.SCALE_SMOOTH);
        ImageIcon img2 = new ImageIcon(i);
        l12 = new JLabel(img2);
        p3.add(l12);

        setLayout(new BorderLayout(10, 10));
        add(p1, "North");
        add(p2, "Center");
        add(p3, "West");

        ch.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                try
                {
                    ConnectionClass obj = new ConnectionClass();
                    String username = ch.getSelectedItem();
                    String q1 = "select * from passenger where username='"+username+"'";
                    ResultSet rest = obj.stm.executeQuery(q1);
                    while (rest.next())
                    {
                        tf1.setText(rest.getString("name"));
                        tf2.setText(rest.getString("age"));
                        tf3.setText(rest.getString("dob"));
                        tf4.setText(rest.getString("address"));
                        tf5.setText(rest.getString("phone"));
                        tf6.setText(rest.getString("email"));
                        tf7.setText(rest.getString("nationality"));
                        tf8.setText(rest.getString("gender"));
                        tf9.setText(rest.getString("passport"));
                    }
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == btn1)
        {
            String username = ch.getSelectedItem();
            String name = tf1.getText();
            String age = tf2.getText();
            String dob = tf3.getText();
            String address = tf4.getText();
            String phone = tf5.getText();
            String email = tf6.getText();
            String nationality = tf7.getText();
            String gender = tf8.getText();
            String passport = tf9.getText();

            try
            {
                ConnectionClass obj = new ConnectionClass();
                String q1 = "update passenger set name='"+name+"', age='"+age+"', dob='"+dob+"', address='"+address+"', phone='"+phone+"', email='"+email+"', nationality='"+nationality+"', gender='"+gender+"', passport='"+passport+"' where username='"+username+"'";
                int a = obj.stm.executeUpdate(q1);
                if (a == 1)
                {
                    JOptionPane.showMessageDialog(null, "your data successfully updated");
                    this.setVisible(false);
                    new ViewPassenger().setVisible(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Please!, Fill all details carefully");
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
        if (e.getSource() == btn2)
        {
            this.setVisible(false);
        }
    }

    public static void main(String[] args)
    {
        new UpdatePassenger().setVisible(true);
    }
}
