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

        Object[][] data = {
                {201, "John", 25, "O+ve", "7550016647", "smokes rarely"},
                {202, "Jane", 30, "AB-ve", "5789029181", "drinks"},
                {203, "Alice", 28, "O+ve", "8947162789", "no habits"},
                {204, "Bob", 35, "B+ve", "8291082756", "hammeorage"}
        };

        // Column names
        String[] columnNames = {"Patient ID", "Name", "Age", "Blood Group", "Phone", "Info"};

        JTable table = new JTable(data, columnNames);

        // Create a scroll pane to hold the table
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the scroll pane to the frame
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
