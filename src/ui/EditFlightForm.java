package ui;

import db.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class EditFlightForm extends JFrame
{
    private JTextField airlineField, sourceField, destField, departField, arriveField, priceField;
    private int flightId;
    private AdminDashboard dashboard;

    public EditFlightForm(int flightId, AdminDashboard dashboard)
    {
        this.flightId = flightId;
        this.dashboard = dashboard;

        setTitle("Edit Flight ID: "+flightId);
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 2, 10, 10));

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

        add(new JLabel("Price:"));
        priceField = new JTextField();
        add(priceField);

        JButton updateBtn = new JButton("Update Flight");
        updateBtn.addActionListener(e -> updateFlight());
        add(new JLabel());
        add(updateBtn);

        loadFlightData();
    }

    private void loadFlightData()
    {
        try (Connection con = DBConnection.getConnection())
        {
            String q = "select * from flights where flight_id = ?";
            PreparedStatement stm = con.prepareStatement(q);
            stm.setInt(1, flightId);
            ResultSet r = stm.executeQuery();

            if (r.next())
            {
                airlineField.setText(r.getString("airline_name"));
                sourceField.setText(r.getString("source"));
                destField.setText(r.getString("destination"));
                departField.setText(r.getTimestamp("departure_time").toString().substring(0, 16));
                arriveField.setText(r.getTimestamp("arrival_time").toString().substring(0, 16));
                priceField.setText(String.valueOf(r.getDouble("price")));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading flight data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateFlight()
    {
        String airline = airlineField.getText().trim();
        String source = sourceField.getText().trim();
        String dest = destField.getText().trim();
        String depart = departField.getText().trim();
        String arrive = arriveField.getText().trim();
        String priceStr = priceField.getText().trim();

        if (airline.isEmpty() || source.isEmpty() || dest.isEmpty() || depart.isEmpty() || arrive.isEmpty() || priceStr.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "All fields are required.");
            return;
        }

        try
        {
            double price = Double.parseDouble(priceStr);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Timestamp depTime = new Timestamp(sdf.parse(depart).getTime());
            Timestamp arrTime = new Timestamp(sdf.parse(arrive).getTime());

            Connection con = DBConnection.getConnection();
            String q = "update flights set airline_name = ?, source = ?, destination = ?, departure_time = ?, arrival_time = ?, price = ? where flight_id = ?";
            PreparedStatement stm = con.prepareStatement(q);
            stm.setString(1, airline);
            stm.setString(2, source);
            stm.setString(3, dest);
            stm.setTimestamp(4, depTime);
            stm.setTimestamp(5, arrTime);
            stm.setDouble(6, price);
            stm.setInt(7, flightId);

            int rows = stm.executeUpdate();
            if (rows > 0)
            {
                JOptionPane.showMessageDialog(this, "Flight updated successfully!");
                dashboard.loadFlightData(); // Refresh table
                dispose();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating flight.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
