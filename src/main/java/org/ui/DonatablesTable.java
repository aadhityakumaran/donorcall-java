package org.ui;

import javax.swing.*;
import java.awt.*;
import org.back.DBConnections;

public class DonatablesTable {

    public DonatablesTable(int donor_id) {
        createAndShowDonatablesUI(donor_id);
    }

    public static void main(String[] args) {
        createAndShowDonatablesUI(8389);
    }

    public static void createAndShowDonatablesUI(int donor_id) {
        JFrame frame = new JFrame("Donatables Table");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        Object[][] data = DBConnections.getDonatables(donor_id);

        // Column names
        String[] columnNames = {"Patient ID", "Name", "Age", "Blood Group", "Phone", "Info"};

        if (data != null) {
            JTable table = new JTable(data, columnNames);
            // Create a scroll pane to hold the table
            JScrollPane scrollPane = new JScrollPane(table);

            // Add the scroll pane to the frame
            frame.add(scrollPane, BorderLayout.CENTER);
        } else {
            frame.add(new Label("No donors in need"));
        }

        frame.setVisible(true);
    }
}
