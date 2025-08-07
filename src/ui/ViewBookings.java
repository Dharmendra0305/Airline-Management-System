package ui;

import db.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ViewBookings extends JFrame
{
    private JTable bookingsTable;
    private DefaultTableModel tableModel;

    public ViewBookings(int flightId)
    {
        setTitle("Bookings for Flight ID: " + flightId);
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Bookings Details", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        add(title, BorderLayout.NORTH);

        tableModel = new DefaultTableModel();
        bookingsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(bookingsTable);
        add(scrollPane, BorderLayout.CENTER);

        tableModel.setColumnIdentifiers(new String[]
                {
                        "Booking ID", "Passenger Name", "Email", "Seat Number", "Payment Status", "Booking Time"
                });

        loadBookingData(flightId);
    }

    private void loadBookingData(int flightId)
    {
        try (Connection con = DBConnection.getConnection())
        {
            String q = "select * from bookings where flight_id = ?";
            PreparedStatement stm = con.prepareStatement(q);
            stm.setInt(1, flightId);
            ResultSet r = stm.executeQuery();

            while (r.next())
            {
                Vector<Object> row = new Vector<>();
                row.add(r.getInt("booking_id"));
                row.add(r.getString("passenger_name"));
                row.add(r.getString("email"));
                row.add(r.getString("seat_number"));
                row.add(r.getString("payment_status"));
                row.add(r.getTimestamp("booking_time"));
                tableModel.addRow(row);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading bookings.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
