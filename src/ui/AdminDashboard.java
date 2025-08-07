package ui;

import db.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Vector;

public class AdminDashboard extends JFrame
{
    private JTable flightTable;
    private DefaultTableModel tableModel;

    public AdminDashboard()
    {
        setTitle("Admin Dashboard");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Admin Dashboard - Flight Management", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        tableModel = new DefaultTableModel();
        flightTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(flightTable);
        add(scrollPane, BorderLayout.CENTER);

        tableModel.setColumnIdentifiers(new String[]
                {
                        "Flight ID", "Airline", "Source", "Destination", "Departure", "Arrival", "Price"
                });

        loadFlightData();

        JPanel btnPanel = new JPanel(new FlowLayout());

        JButton addBtn = new JButton("Add Flight");
        JButton editBtn = new JButton("Edit Flight");
        JButton deleteBtn = new JButton("Delete Flight");
        JButton viewBookingsBtn = new JButton("View Bookings");

        btnPanel.add(addBtn);
        btnPanel.add(editBtn);
        btnPanel.add(deleteBtn);
        btnPanel.add(viewBookingsBtn);
        add(btnPanel, BorderLayout.SOUTH);

        addBtn.addActionListener(e ->
        {
            new AddFlightForm(this).setVisible(true);
        });

        editBtn.addActionListener(e ->
        {
            int selectedRow = flightTable.getSelectedRow();
            if (selectedRow == -1)
            {
                JOptionPane.showMessageDialog(this, "please select a flight to edit.");
                return;
            }

            int flightId = (int) tableModel.getValueAt(selectedRow, 0);
            new EditFlightForm(flightId, AdminDashboard.this).setVisible(true);
        });

        deleteBtn.addActionListener(e -> deleteSelectedFlight());

        viewBookingsBtn.addActionListener(e ->
        {
            int selectedRow = flightTable.getSelectedRow();
            if (selectedRow == -1)
            {
                JOptionPane.showMessageDialog(this, "Please select a flight.");
                return;
            }

            int flightId = (int) tableModel.getValueAt(selectedRow, 0);
            new ViewBookings(flightId).setVisible(true);
        });
    }

    public void loadFlightData()
    {
        tableModel.setRowCount(0); // clear table
        try (Connection con = DBConnection.getConnection())
        {
            String q = "select * from flights";
            PreparedStatement stm = con.prepareStatement(q);
            ResultSet r = stm.executeQuery();

            while (r.next())
            {
                Vector<Object> row = new Vector<>();
                row.add(r.getInt("flight_id"));
                row.add(r.getString("airline_name"));
                row.add(r.getString("source"));
                row.add(r.getString("destination"));
                row.add(r.getTimestamp("departure_time"));
                row.add(r.getTimestamp("arrival_time"));
                row.add(r.getDouble("price"));
                tableModel.addRow(row);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading flights.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteSelectedFlight()
    {
        int row = flightTable.getSelectedRow();
        if (row == -1)
        {
            JOptionPane.showMessageDialog(this, "Please select a flight to delete.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this flight?", "Confirm Delete", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION)
        {
            int flightID = (int) tableModel.getValueAt(row, 0);
            try (Connection con = DBConnection.getConnection())
            {
                PreparedStatement stm = con.prepareStatement("Delete from flights where flight_id = ?");
                stm.setInt(1, flightID);
                stm.executeUpdate();
                JOptionPane.showMessageDialog(this, "Flight deleted");
                loadFlightData(); // refresh table
            }
            catch (SQLException e)
            {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Could not delete flight.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
