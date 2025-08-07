package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen extends JFrame
{
    public HomeScreen()
    {
        setTitle("Airline Booking System");
        setSize(400, 300);
        setLocationRelativeTo(null); // center window
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Welcome to Indigo Airline Booking", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));

        JButton bookFlightBtn = new JButton("Book a Flight");
        JButton cancelBookingBtn = new JButton("Cancel Booking");
        JButton adminLoginBtn = new JButton("Admin Login");

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        buttonPanel.add(bookFlightBtn);
        buttonPanel.add(cancelBookingBtn);
        buttonPanel.add(adminLoginBtn);

        add(buttonPanel, BorderLayout.CENTER);

        bookFlightBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JOptionPane.showMessageDialog(HomeScreen.this, "Book Flight module will open (next)");
            }
        });

        cancelBookingBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JOptionPane.showMessageDialog(HomeScreen.this, "Cancel Booking module will open (later)");
            }
        });

        adminLoginBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                new AdminLogin().setVisible(true);
            }
        });
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() ->
        {
            new HomeScreen().setVisible(true);
        });
    }
}
