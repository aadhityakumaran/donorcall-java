package org.ui;

import javax.swing.*;
import java.awt.*;
import org.back.DBConnections;

public class ViewProfile {
    public ViewProfile(int user_id) {
        createAndShowViewProfile(user_id);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ViewProfile(8389));
    }

    public void createAndShowViewProfile(int user_id){
        JFrame frame = new JFrame("User Information");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2)); // 2 columns for label-value pairs

        String[] userData = DBConnections.getUserData(user_id);

        if (userData != null) {
            addLabelValuePair(panel, "Name:", userData[0]);
            addLabelValuePair(panel, "Phone:", userData[1]);
            addLabelValuePair(panel, "Blood Type:", userData[2]);

            if (userData[3] == null) userData[3] = "Never";
            addLabelValuePair(panel, "Last Donated:", userData[3]);
        }

        // Create a new JPanel for the "Back" button and set a FlowLayout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Center the button

        JButton backButton = new JButton("Back");
        buttonPanel.add(backButton);

        // Add the label panel to the frame's content pane at the top
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        // Add the button panel to the frame's content pane at the bottom
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);

        backButton.addActionListener(e -> {
            frame.dispose();
            SwingUtilities.invokeLater(() -> new UserDashboard(user_id));
        });

    }

    private static void addLabelValuePair(JPanel panel, String label, String value) {
        JLabel labelComponent = new JLabel(label);
        JLabel valueComponent = new JLabel(value);
        panel.add(labelComponent);
        panel.add(valueComponent);
    }

}
