package org.ui;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class DataEntryUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(DataEntryUI::createAndShowDataEntryGUI);
    }

    public static void createAndShowDataEntryGUI() {
        JFrame frame = new JFrame("Data Entry Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(5, 2)); // GridLayout with 5 rows and 2 columns

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

            if (insertDataIntoDatabase(name, mobileNumber, yearOfStudy, bloodGroup)) {
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

    private static boolean insertDataIntoDatabase(String name, String mobileNumber, int yearOfStudy, String bloodGroup) {
        try {
            String jdbcUrl = "jdbc:mysql://localhost:3306/Blood";
            String dbUsername = "root";
            String dbPassword = "tang";

            Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);

            String insertQuery = "INSERT INTO PersonInfo (name, mobile_number, year_of_study, blood_group) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, mobileNumber);
            preparedStatement.setInt(3, yearOfStudy);
            preparedStatement.setString(4, bloodGroup);

            int rowsInserted = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            return rowsInserted > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
