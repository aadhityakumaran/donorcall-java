package org.Donor;

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
        frame.setLayout(new GridLayout(0, 1)); // Set to a single column
        ImageIcon icon1 = new ImageIcon("src\\main\\resources\\images\\blood drop.jpg");
        frame.setIconImage(icon1.getImage());


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton viewProfileButton = new JButton("View Profile");
        buttonPanel.add(viewProfileButton);

        JButton viewCalls = new JButton("Donation Panel");
        buttonPanel.add(viewCalls);

        JButton backButton = new JButton("Back");
        buttonPanel.add(backButton);

        frame.add(viewProfileButton);
        frame.add(viewCalls);
        frame.add(backButton);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        viewProfileButton.addActionListener(e -> {
            // Open ViewProfile
            SwingUtilities.invokeLater(() -> new ViewProfile(user_id));
            frame.dispose();
        });

        viewCalls.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> new DonatablesTable(user_id));
            frame.dispose();
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
