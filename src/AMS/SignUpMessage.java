package AMS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SignUpMessage extends JFrame implements ActionListener
{
    JLabel l1, l2, l3, l4;
    JTextField tf1;
    JPasswordField pf;
    JButton btn1, btn2;
    JFrame f;

    SignUpMessage()
    {
        f = new JFrame("Create Account");
        f.setBackground(Color.WHITE);
        f.setLayout(null);

        l1 = new JLabel();
        l1.setBounds(0, 0, 580, 350);
        l1.setLayout(null);
        f.add(l1);

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("AMS/Icons/9.jpg"));
        Image i = img.getImage().getScaledInstance(580, 350, Image.SCALE_SMOOTH);
        ImageIcon img2 = new ImageIcon(i);
        l1.setIcon(img2);

        l2 = new JLabel("Sign Up");
        l2.setBounds(220, 30, 500, 50);
        l2.setForeground(Color.BLACK);
        l2.setFont(new Font("Arial", Font.BOLD, 30));
        l1.add(l2);

        l3 = new JLabel("Username");
        l3.setBounds(120, 120, 150, 30);
        l3.setForeground(Color.BLACK);
        l3.setFont(new Font("Arial", Font.BOLD, 20));
        l1.add(l3);

        tf1 = new JTextField();
        tf1.setBounds(320, 120, 150, 30);
        l1.add(tf1);

        l4 = new JLabel("Password");
        l4.setBounds(120, 170, 150, 30);
        l4.setForeground(Color.BLACK);
        l4.setFont(new Font("Arial", Font.BOLD, 20));
        l1.add(l4);

        pf = new JPasswordField();
        pf.setBounds(320, 170, 150, 30);
        l1.add(pf);

        btn1 = new JButton("Create Account");
        btn1.setBackground(Color.BLACK);
        btn1.setForeground(Color.WHITE);
        btn1.setBounds(120, 220, 150, 40);
        l1.add(btn1);

        btn2 = new JButton("Back");
        btn2.setBackground(Color.BLACK);
        btn2.setForeground(Color.WHITE);
        btn2.setBounds(320, 220, 150, 40);
        l1.add(btn2);

        btn1.addActionListener(this);
        btn2.addActionListener(this);

        f.setVisible(true);
        f.setSize(580, 350);
        f.setLocation(300, 100);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == btn1)
        {
            String user = tf1.getText().trim();
            String pass = new String(pf.getPassword()).trim();

            if (user.isEmpty() || pass.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Username and Password cannot be empty.");
                return;
            }

            try
            {
                ConnectionClass obj = new ConnectionClass();
                String q1 = "select * from signup where username = ?";
                PreparedStatement check = obj.con.prepareStatement(q1);
                check.setString(1, user);
                ResultSet r = check.executeQuery();

                if (r.next())
                {
                    JOptionPane.showMessageDialog(null, "Username already exists. Try another.");
                }

                String q = "insert into signup(username, password) values('"+user+"', '"+pass+"')";
                obj.stm.executeUpdate(q);
                JOptionPane.showMessageDialog(null, "Account Created Successfully!");
                f.setVisible(false);
                new Login();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
        if (e.getSource() == btn2)
        {
            f.setVisible(false);
            new Login();
        }
    }

    public static void main(String[] args)
    {
        new SignUpMessage().setVisible(true);
    }
}
