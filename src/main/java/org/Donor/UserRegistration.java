package org.Donor;

import org.back.DBConnections;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class UserRegistration {
    public UserRegistration() {
        createAndShowDataEntryGUI();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UserRegistration::new);
    }

    public void createAndShowDataEntryGUI() {
        JFrame frame = new JFrame("Registration Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(6, 2));
        ImageIcon icon1 = new ImageIcon("src\\main\\resources\\images\\blood drop.jpg");
        frame.setIconImage(icon1.getImage());


        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        frame.add(nameLabel);
        frame.add(nameField);

        JLabel pwdLabel = new JLabel("Password:");
        JPasswordField pwdField = new JPasswordField();
        frame.add(pwdLabel);
        frame.add(pwdField);

        JLabel confLabel = new JLabel("Confirm Password:");
        JPasswordField confField = new JPasswordField();
        frame.add(confLabel);
        frame.add(confField);

        JLabel phoneLabel = new JLabel("Phone:");
        JTextField phoneField = new JTextField();
        frame.add(phoneLabel);
        frame.add(phoneField);

        JLabel bloodGroupLabel = new JLabel("Blood Group:");
        JTextField bloodGroupField = new JTextField();
        frame.add(bloodGroupLabel);
        frame.add(bloodGroupField);

        JButton submitButton = new JButton("Submit");
        frame.add(submitButton);

        JButton backButton = new JButton("Back");
        frame.add(backButton);

        submitButton.addActionListener(e -> {
            char[] pwd = pwdField.getPassword();
            char[] conf = confField.getPassword();
            String name = nameField.getText();
            String phone = phoneField.getText();
            String bloodGroup = bloodGroupField.getText();

            if (name.isEmpty() || String.valueOf(pwd).isEmpty() || String.valueOf(conf).isEmpty() || phone.isEmpty() || bloodGroup.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill in all the required fields.");
            } else if (Arrays.equals(pwd, conf)) {
                int user_id = DBConnections.registerNewUser(String.valueOf(pwd), name, phone, bloodGroup);
                if (user_id != -1) {
                    JOptionPane.showMessageDialog(frame, "Data has been successfully submitted. Your donor_id is " + user_id);
                    frame.dispose();
                    SwingUtilities.invokeLater(() -> new UserDashboard(user_id));
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to submit data. Please try again.");
                    // Clear the input fields
                    nameField.setText("");
                    phoneField.setText("");
                    pwdField.setText("");
                    bloodGroupField.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Password and confirm password do not match");
            }
        });

        backButton.addActionListener(e -> {
            frame.dispose();
            SwingUtilities.invokeLater(Donor::createAndShowLoginGUI);
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
