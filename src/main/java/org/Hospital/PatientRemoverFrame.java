package org.Hospital;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import org.back.DBConnections;
class PatientRemoverFrame extends JFrame {

    private final JTextField donorIDField;
    private final JCheckBox wasDonorCheckBox;
    private final JTable table;

    public PatientRemoverFrame(JFrame parentFrame, JTable table) {
        this.table = table;
        setTitle("Patient Remover");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create a checkbox
        wasDonorCheckBox = new JCheckBox("Was there a donor");
        mainPanel.add(wasDonorCheckBox, BorderLayout.NORTH);

        // Create a label and text field
        JLabel donorIDLabel = new JLabel("Donor ID: ");
        donorIDField = new JTextField(20);
        donorIDField.setEnabled(false);

        JPanel donorIDPanel = new JPanel(new FlowLayout());
        donorIDPanel.add(donorIDLabel);
        donorIDPanel.add(donorIDField);

        mainPanel.add(donorIDPanel, BorderLayout.CENTER);

        // Create a button panel
        JPanel buttonPanel = new JPanel();

        // Create a "Remove" button
        JButton removeButton = new JButton("Remove");

        // Add action listeners to the checkbox and "Remove" button
        wasDonorCheckBox.addItemListener(e -> {
            boolean isSelected = wasDonorCheckBox.isSelected();
            donorIDField.setEnabled(isSelected);
        });

        removeButton.addActionListener(e -> {
            // Add the logic to remove the patient here
            if (wasDonorCheckBox.isSelected()) {
                DBConnections.updateDonorDate(Integer.parseInt(donorIDField.getText()));
            }
            DBConnections.removePatient((int) table.getValueAt(table.getSelectedRow(), 0));
            removeSelectedRowFromTable();
            parentFrame.setEnabled(true);
            dispose();
        });

        // Add buttons to the button panel
        buttonPanel.add(removeButton);

        // Add panels to the main frame
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);

        // Add a window listener to enable the parentFrame and bring it to the front when this frame is closed
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                parentFrame.setEnabled(true);
                parentFrame.toFront();
            }
        });
    }

    private void removeSelectedRowFromTable() {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.removeRow(table.getSelectedRow());
    }
}
