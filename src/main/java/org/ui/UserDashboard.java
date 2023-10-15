package org.ui;

import javax.swing.*;
import java.awt.*;

public class UserDashboard {

    public UserDashboard(int user_id) {
        createAndShowUserDashboard(user_id);
    }
    public void createAndShowUserDashboard(int user_id) {
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

        JButton backButton = new JButton("Back");
        buttonPanel.add(backButton);

        frame.add(uploadDataButton);
        frame.add(callDonorsButton);
        frame.add(backButton);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        uploadDataButton.addActionListener(e -> {
            // Open DataEntryUI
            SwingUtilities.invokeLater(UserRegistration::new);
            frame.dispose();
        });

        callDonorsButton.addActionListener(e -> {
            // Add your code for "Call for donors" here
            JOptionPane.showMessageDialog(frame, "Call for donors functionality will be implemented here.");
        });

        backButton.addActionListener(e -> {
            frame.dispose();
            SwingUtilities.invokeLater(Donor::createAndShowLoginGUI);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UserDashboard(8389));
    }
}

