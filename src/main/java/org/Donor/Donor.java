package org.Donor;

import javax.swing.*;
import java.awt.*;
import org.back.DBConnections;

public class Donor {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Donor::createAndShowLoginGUI);
    }

    public static void createAndShowLoginGUI() {
        JFrame frame = new JFrame("Login Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new BorderLayout());

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(4, 2));

        JLabel userLabel = new JLabel("Donor ID:");
        JTextField userField = new JTextField();
        loginPanel.add(userLabel);
        loginPanel.add(userField);

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginPanel.add(loginButton);

        JButton newUserButton = new JButton("New User");
        loginPanel.add(newUserButton);

        frame.add(loginPanel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);

        loginButton.addActionListener(e -> {
            try {
                int username = Integer.parseInt(userField.getText());
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);

                if (1000 <= username && username < 10000){
                    JOptionPane.showMessageDialog(frame, "Donor ID must be a 4-digit number");
                } else if (DBConnections.isValidLogin(username, password)) {
                    // Close the login window
                    frame.dispose();
                    // Open the user dashboard
                    // Create and show the user dashboard
                    SwingUtilities.invokeLater(() -> new UserDashboard(username));
                } else {
                    JOptionPane.showMessageDialog(frame, "Login failed. Please check your credentials.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Donor ID must be a 4-digit number");
            }

            passwordField.setText(""); // Clear the password field for security
        });

        newUserButton.addActionListener(e -> {
            frame.dispose();
            SwingUtilities.invokeLater(UserRegistration::new);
        });

        frame.setVisible(true);
    }

}