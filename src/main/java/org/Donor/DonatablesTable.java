package org.Donor;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.back.DBConnections;

public class DonatablesTable {

    public DonatablesTable(int donor_id) {
        createAndShowDonatablesUI(donor_id);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DonatablesTable(8389));
    }

    public void createAndShowDonatablesUI(int donor_id) {
        JFrame frame = new JFrame("Donatables Table");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        Object[][] data = DBConnections.getDonatables(donor_id);

        // Column names
        String[] columnNames = {"Patient ID", "Name", "Age", "Blood Group", "Phone", "Info"};

        if (data != null) {
            DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Make all cells non-editable
                }
            };
            JTable table = new JTable(tableModel);

            // Disable column reordering
            table.getTableHeader().setReorderingAllowed(false);

            // Center-align the content in cells
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            for (int i = 0; i < table.getColumnCount(); i++) {
                table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

            // Set column widths
            table.getColumnModel().getColumn(0).setPreferredWidth(60); // Patient ID
            table.getColumnModel().getColumn(1).setPreferredWidth(100); // Name
            table.getColumnModel().getColumn(2).setPreferredWidth(40); // Age
            table.getColumnModel().getColumn(3).setPreferredWidth(60); // Blood Group
            table.getColumnModel().getColumn(4).setPreferredWidth(80); // Phone
            table.getColumnModel().getColumn(5).setPreferredWidth(200); // Info

            // Make table cells uneditable
            for (int row = 0; row < table.getRowCount(); row++) {
                for (int column = 0; column < table.getColumnCount(); column++) {
                    table.isCellEditable(row, column);
                }
            }

            JScrollPane scrollPane = new JScrollPane(table);
            frame.add(scrollPane, BorderLayout.CENTER);
        } else {
            frame.add(new JLabel("No donors in need"), BorderLayout.CENTER);
        }

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));

        backButton.addActionListener(e -> {
            frame.dispose();
            SwingUtilities.invokeLater(() -> new UserDashboard(donor_id));
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(backButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
