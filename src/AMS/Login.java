package AMS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener
{
    JLabel l1, l2, l3, l4;
    JButton btn1, btn2;
    JPasswordField pf;
    JTextField tf;
    JFrame f;

    Login()
    {
        f = new JFrame("Login Account");
        f.setBackground(Color.WHITE);
        f.setLayout(null);

        l1 = new JLabel();
        l1.setBounds(0 , 0, 580, 350);
        l1.setLayout(null);
        f.add(l1);

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("AMS/Icons/1.jpg"));
        Image i1 = img.getImage().getScaledInstance(580, 350, Image.SCALE_SMOOTH);
        ImageIcon img2 = new ImageIcon(i1);
        l1.setIcon(img2);

        l2 = new JLabel("Login Account");
        l2.setBounds(190 , 30, 500, 50);
        l2.setForeground(Color.BLACK);
        l2.setFont(new Font("Arial", Font.BOLD, 30));
        l1.add(l2);

        l3 = new JLabel("Username");
        l3.setBounds(120 , 120, 150, 30);
        l3.setForeground(Color.BLACK);
        l3.setFont(new Font("Arial", Font.BOLD, 20));
        l1.add(l3);

        tf = new JTextField();
        tf.setBounds(320, 120, 150, 30);
        l1.add(tf);

        l4 = new JLabel("Password");
        l4.setBounds(120 , 170, 150, 30);
        l4.setForeground(Color.BLACK);
        l4.setFont(new Font("Arial", Font.BOLD, 20));
        l1.add(l4);

        pf = new JPasswordField();
        pf.setBounds(320, 170, 150, 30);
        l1.add(pf);

        btn1 = new JButton("Login");
        btn1.setBackground(Color.BLACK);
        btn1.setForeground(Color.WHITE);
        btn1.setBounds(120, 220, 150, 40);
        l1.add(btn1);

        btn2 = new JButton("SignUp");
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
            String username = tf.getText();
            String pass = pf.getText();

            try
            {
                ConnectionClass obj = new ConnectionClass();
                String q = "Select * from signup where username='"+username+"' and password='"+pass+"'";
                ResultSet rs = obj.stm.executeQuery(q);
                if (rs.next())
                {
                    new HomePage().setVisible(true);
                    f.setVisible(false);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "You have entered Wrong Username and Password!!");
                    f.setVisible(false);
                    f.setVisible(true);
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (e.getSource() == btn2)
        {
            this.setVisible(false);
            new SignUpMessage();
        }
    }

    public static void main(String[] args)
    {
        new Login();
    }
}
