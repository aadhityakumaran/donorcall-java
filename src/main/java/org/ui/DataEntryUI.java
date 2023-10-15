package org.ui;

import javax.swing.*;
import java.awt.*;
import org.back.Donor_back;

public class DataEntryUI {
    public DataEntryUI() {
        createAndShowDataEntryGUI();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DataEntryUI::new);
    }

    public void createAndShowDataEntryGUI() {
        JFrame frame = new JFrame("Registration Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(5, 2));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        frame.add(nameLabel);
        frame.add(nameField);

        JLabel pwdLabel = new JLabel("Password:");
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

        submitButton.addActionListener(e -> {
            String pwd = pwdField.getText();
            String name = nameField.getText();
            String phone = phoneField.getText();
            String bloodGroup = bloodGroupField.getText();

            int user_id = Donor_back.insertDataIntoDatabase(pwd, name, phone, bloodGroup);
            if (user_id != -1) {
                JOptionPane.showMessageDialog(frame, "Data has been successfully submitted. Your donor_id is " + user_id);
                frame.dispose();
                SwingUtilities.invokeLater(UserDashboard::new);
            } else {
                JOptionPane.showMessageDialog(frame, "Failed to submit data. Please try again.");
                // Clear the input fields
                nameField.setText("");
                phoneField.setText("");
                pwdField.setText("");
                bloodGroupField.setText("");
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


}
