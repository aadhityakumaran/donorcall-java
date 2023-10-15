package org.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserDashboard {
    public UserDashboard() {
        JFrame frame = new JFrame("User Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(2, 1));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton uploadDataButton = new JButton("Upload Your Data in DB");
        buttonPanel.add(uploadDataButton);

        JButton callDonorsButton = new JButton("Call for Donors");
        buttonPanel.add(callDonorsButton);

        frame.add(uploadDataButton);
        frame.add(callDonorsButton);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        uploadDataButton.addActionListener(e -> {
            // Open DataEntryUI
            SwingUtilities.invokeLater(DataEntryUI::new);
            frame.dispose();
        });

        callDonorsButton.addActionListener(e -> {
            // Add your code for "Call for donors" here
            JOptionPane.showMessageDialog(frame, "Call for donors functionality will be implemented here.");
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UserDashboard::new);
    }
}

