package org.ui;

import javax.swing.*;
import java.awt.*;
import org.back.DBConnections;

public class PatientRegistration {
    public PatientRegistration() {
        createAndShowDataEntryGUI();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PatientRegistration::createAndShowDataEntryGUI);
    }

    public static void createAndShowDataEntryGUI() {
        JFrame frame = new JFrame("Patient Registration Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250); // Increased the height for better spacing
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        JLabel ageLabel = new JLabel("Age:");
        JTextField ageField = new JTextField();
        inputPanel.add(ageLabel);
        inputPanel.add(ageField);

        JLabel infoLabel = new JLabel("Info:");
        JTextField infoField = new JTextField();
        inputPanel.add(infoLabel);
        inputPanel.add(infoField);

        JLabel phoneLabel = new JLabel("Phone:");
        JTextField phoneField = new JTextField();
        inputPanel.add(phoneLabel);
        inputPanel.add(phoneField);

        JLabel bloodGroupLabel = new JLabel("Blood Group:");
        JTextField bloodGroupField = new JTextField();
        inputPanel.add(bloodGroupLabel);
        inputPanel.add(bloodGroupField);

        frame.add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton submitButton = new JButton("Submit");
        buttonPanel.add(submitButton);

        JButton backButton = new JButton("Back");
        buttonPanel.add(backButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        submitButton.addActionListener(e -> {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String blood = bloodGroupField.getText();
            String phone = phoneField.getText();
            String info = infoField.getText();

            int user_id = DBConnections.registerNewPatient(name, age, blood, phone, info);
            if (user_id != -1) {
                JOptionPane.showMessageDialog(frame, "Data has been successfully submitted.");
            } else {
                JOptionPane.showMessageDialog(frame, "Failed to submit data. Please try again.");
            }

            // Clear the input fields
            nameField.setText("");
            ageField.setText("");
            phoneField.setText("");
            infoField.setText("");
            bloodGroupField.setText("");
        });


        backButton.addActionListener(e -> {
            frame.dispose();
            SwingUtilities.invokeLater(Hospital::createAndShowHospitalUI);
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
