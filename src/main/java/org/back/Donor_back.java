package org.back;

import java.sql.*;
import java.util.Random;

public class Donor_back {

    static String jdbcUrl = "jdbc:mysql://localhost:3306/blood";
    static String dbUsername = "root";
    static String dbPassword = "sqlsme";
    public static boolean isValidLogin(int donorID, String password) {
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);

            String selectQuery = "SELECT * FROM users WHERE donor_id = ? AND pwd = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, donorID);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            boolean isValid = resultSet.next();

            resultSet.close();
            preparedStatement.close();
            connection.close();

            return isValid;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static int registerNewUser(String pwd, String name, String mobileNumber, String bloodGroup) {
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            Random rand = new Random();
            int donor_id = rand.nextInt(10000);
            String insertQuery = "INSERT INTO users (donor_id, pwd, phone, name, blood) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, donor_id);
            preparedStatement.setString(2, pwd);
            preparedStatement.setString(3, mobileNumber);
            preparedStatement.setString(4, name);
            preparedStatement.setString(5, bloodGroup);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted == 0)
                return -1;
            preparedStatement.close();
            connection.close();

            return donor_id;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public static String[] getUserData(int donorID) {
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);

            String query = "SELECT name, phone, blood, last_don FROM users WHERE donor_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, donorID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String bloodType = resultSet.getString("blood");
                String phoneNumber = resultSet.getString("phone");
                String last_don = resultSet.getString("last_don");
                return new String[]{name, phoneNumber, bloodType, last_don};
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int registerNewPatient(String name, int age, String bloodGroup, String mobileNumber, String info) {
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            Random rand = new Random();
            int patient_id = rand.nextInt(10000);
            String insertQuery = "INSERT INTO hospital (patient_id, name, age, blood, phone, info) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, patient_id);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, age);
            preparedStatement.setString(4, bloodGroup);
            preparedStatement.setString(5, mobileNumber);
            preparedStatement.setString(6, info);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted == 0)
                return -1;
            preparedStatement.close();
            connection.close();

            return patient_id;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }
}
