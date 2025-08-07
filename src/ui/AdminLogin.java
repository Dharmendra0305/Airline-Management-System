package ui;

import db.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminLogin extends JFrame
{
    private JTextField usernameField;
    private JPasswordField passwordField;

    public AdminLogin()
    {
        setTitle("Admin Login");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title  = new JLabel("Admin Login", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        add(title, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        formPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        formPanel.add(usernameField);

        formPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);

        add(formPanel, BorderLayout.CENTER);

        JButton loginBtn = new JButton("Login");
        add(loginBtn, BorderLayout.SOUTH);

        loginBtn.addActionListener(e -> handleLogin());
    }

    private void handleLogin()
    {
        String username = usernameField.getText().trim();
        String password = String.valueOf(passwordField.getPassword());

        try (Connection conn = DBConnection.getConnection())
        {
            String q = "select * from admins where username = ? and password = ?";
            PreparedStatement stm = conn.prepareStatement(q);
            stm.setString(1, username);
            stm.setString(2, password);

            ResultSet r = stm.executeQuery();
            if (r.next())
            {
                JOptionPane.showMessageDialog(this, "Login successful!");
                dispose();
                new AdminDashboard().setVisible(true);
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error occured", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
