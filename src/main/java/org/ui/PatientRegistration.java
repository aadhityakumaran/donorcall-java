package org.ui;

import javax.swing.*;
import java.awt.*;
import org.back.Donor_back;

public class PatientRegistration {
    public PatientRegistration() {
        createAndShowDataEntryGUI();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PatientRegistration::new);
    }

    public void createAndShowDataEntryGUI() {
        JFrame frame = new JFrame("Patient Registration Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(5, 2));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        frame.add(nameLabel);
        frame.add(nameField);

        JLabel pwdLabel = new JLabel("Patient ID:");
        JTextField pwdField = new JTextField();
        frame.add(pwdLabel);
        frame.add(pwdField);

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
            String pwd = pwdField.getText();
            String name = nameField.getText();
            String phone = phoneField.getText();
            String bloodGroup = bloodGroupField.getText();

            int user_id = Donor_back.insertDataIntoDatabase(pwd, name, phone, bloodGroup);
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
        });

        backButton.addActionListener(e -> {
            frame.dispose();
            SwingUtilities.invokeLater(Donor::createAndShowLoginGUI);
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
