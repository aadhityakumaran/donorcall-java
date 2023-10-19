package org.Hospital;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.back.DBConnections;

public class PatientsTable {

    public PatientsTable() {
        createAndShowPatientsUI();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PatientsTable::new);
    }

    public void createAndShowPatientsUI() {
        JFrame frame = new JFrame("Patients Table");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        ImageIcon icon1 = new ImageIcon("src\\main\\resources\\images\\blood drop.jpg");
        frame.setIconImage(icon1.getImage());


        Object[][] data = DBConnections.getPatients();

        // Column names
        String[] columnNames = {"Patient ID", "Name", "Age", "Blood Group", "Phone", "Info"};

        JTable table;

        if (data != null) {
            DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Make all cells non-editable
                }
            };
            table = new JTable(tableModel);
            table.getTableHeader().setReorderingAllowed(false); // Disable column reordering

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

            JScrollPane scrollPane = new JScrollPane(table);
            frame.add(scrollPane, BorderLayout.CENTER);

        } else {
            table = new JTable(new Object[0][columnNames.length], columnNames);
            frame.add(new Label("No Patients"));
        }

        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow only one row to be selected
        selectionModel.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                // Get the selected row and do something with it
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    // Selected row index is valid
                    // Access data from the selected row
                    int patientID = (int) table.getValueAt(selectedRow, 0);
                    String name = (String) table.getValueAt(selectedRow, 1);

                    // Perform actions with the selected data
                    System.out.println("Selected Patient ID: " + patientID);
                    System.out.println("Selected Name: " + name);
                }
            }
        });

        JButton removalButton = new JButton("Remove");
        removalButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int patientID = (int) table.getValueAt(selectedRow, 0);
                System.out.println("Selected Patient ID (from button click): " + patientID);

                frame.setEnabled(false);

                JFrame patientRemoverFrame = new PatientRemoverFrame(frame, table);
                patientRemoverFrame.setVisible(true);
            }
        });

        removalButton.setFont(new Font("Arial", Font.BOLD, 16));

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));

        backButton.addActionListener(e -> {
            frame.dispose();
            SwingUtilities.invokeLater(Hospital::createAndShowHospitalUI);
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(removalButton);
        buttonPanel.add(backButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
