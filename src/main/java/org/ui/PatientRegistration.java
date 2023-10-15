package org.ui;

import javax.swing.*;
import java.awt.*;
import org.back.Donor_back;

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
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(5, 2));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        frame.add(nameLabel);
        frame.add(nameField);

        JLabel ageLabel = new JLabel("Age:");
        JTextField ageField = new JTextField();
        frame.add(ageLabel);
        frame.add(ageField);

        JLabel infoLabel = new JLabel("Info:");
        JTextField infoField = new JTextField();
        frame.add(infoLabel);
        frame.add(infoField);

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
            String name = nameField.getText();
            int age =Integer.parseInt(ageField.getText());
            String blood = bloodGroupField.getText();
            String phone = phoneField.getText();
            String info = infoField.getText();

            int user_id = Donor_back.registerNewPatient( name, age, blood, phone, info);
            if (user_id != -1) {
                JOptionPane.showMessageDialog(frame, "Data has been successfully submitted.");
                frame.dispose();
                SwingUtilities.invokeLater(() -> new UserDashboard(user_id));
            } else {
                JOptionPane.showMessageDialog(frame, "Failed to submit data. Please try again.");
                // Clear the input fields
            }
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
