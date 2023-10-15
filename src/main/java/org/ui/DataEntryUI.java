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
        JFrame frame = new JFrame("Data Entry Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(5, 2));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        frame.add(nameLabel);
        frame.add(nameField);

        JLabel mobileLabel = new JLabel("Mobile Number:");
        JTextField mobileField = new JTextField();
        frame.add(mobileLabel);
        frame.add(mobileField);

        JLabel yearLabel = new JLabel("Year of Study:");
        JTextField yearField = new JTextField();
        frame.add(yearLabel);
        frame.add(yearField);

        JLabel bloodGroupLabel = new JLabel("Blood Group:");
        JTextField bloodGroupField = new JTextField();
        frame.add(bloodGroupLabel);
        frame.add(bloodGroupField);

        JButton submitButton = new JButton("Submit");
        frame.add(submitButton);

        submitButton.addActionListener(e -> {
            String name = nameField.getText();
            String mobileNumber = mobileField.getText();
            int yearOfStudy = Integer.parseInt(yearField.getText());
            String bloodGroup = bloodGroupField.getText();

            if (Donor_back.insertDataIntoDatabase(name, mobileNumber, yearOfStudy, bloodGroup)) {
                JOptionPane.showMessageDialog(frame, "Data has been successfully submitted.");
            } else {
                JOptionPane.showMessageDialog(frame, "Failed to submit data. Please try again.");
            }

            // Clear the input fields
            nameField.setText("");
            mobileField.setText("");
            yearField.setText("");
            bloodGroupField.setText("");
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


}
