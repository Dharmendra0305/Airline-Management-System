package ui;

import db.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

public class AddFlightForm extends JFrame
{
    private JTextField airlineField, sourceField, destField, departField, arriveField, priceField;
    private AdminDashboard dashboard;

    public AddFlightForm(AdminDashboard dashboard)
    {
        this.dashboard = dashboard;

        setTitle("Add new Flight");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 2, 10, 10));
        setResizable(false);

        add(new JLabel("Airline Name:"));
        airlineField = new JTextField();
        add(airlineField);

        add(new JLabel("Source:"));
        sourceField = new JTextField();
        add(sourceField);

        add(new JLabel("Destination:"));
        destField = new JTextField();
        add(destField);

        add(new JLabel("Departure (yyyy-MM-dd HH:mm):"));
        departField = new JTextField();
        add(departField);

        add(new JLabel("Arrival (yyyy-MM-dd HH:mm):"));
        arriveField = new JTextField();
        add(arriveField);

        add(new JLabel("Price"));
        priceField = new JTextField();
        add(priceField);

        JButton addButton = new JButton("Add Flight");
        addButton.addActionListener(e -> insertFlight());
        add(new JLabel()); // Empty cell
        add(addButton);
    }

    private void insertFlight()
    {
        String airline = airlineField.getText().trim();
        String source = sourceField.getText().trim();
        String dest = destField.getText().trim();
        String depart = departField.getText().trim();
        String arrive = arriveField.getText().trim();
        String priceStr = priceField.getText().trim();

        if (airline.isEmpty() || source.isEmpty() || dest.isEmpty() || depart.isEmpty() || arrive.isEmpty() || priceStr.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "All fields are required.", "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try
        {
            double price = Double.parseDouble(priceStr);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            java.util.Date depDate = sdf.parse(depart);
            java.util.Date arrDate = sdf.parse(arrive);

            java.sql.Timestamp depSQL = new java.sql.Timestamp(depDate.getTime());
            java.sql.Timestamp arrSQL = new java.sql.Timestamp(arrDate.getTime());

            Connection con = DBConnection.getConnection();
            String q = "Insert into flights(airline_name, source, destination, departure_time, arrival_time, price) values(?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = con.prepareStatement(q);
            stm.setString(1, airline);
            stm.setString(2, source);
            stm.setString(3, dest);
            stm.setTimestamp(4, depSQL);
            stm.setTimestamp(5, arrSQL);
            stm.setDouble(6, price);

            int rows = stm.executeUpdate();
            if (rows > 0)
            {
                JOptionPane.showMessageDialog(this, "Flight added successfully!");
                dashboard.loadFlightData(); // refresh admin dashboard
                dispose(); // close form
            }
        }
        catch (NumberFormatException ex)
        {
            JOptionPane.showMessageDialog(this, "Invalid price format.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding flight", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
