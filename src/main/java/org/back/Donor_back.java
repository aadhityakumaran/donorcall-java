package org.back;

import java.sql.*;
import java.util.Random;

public class Donor_back {

    public static boolean isValidLogin(int donorID, String password) {
        try {
            String jdbcUrl = "jdbc:mysql://localhost:3306/blood";
            String dbUsername = "root";
            String dbPassword = "sqlsme";

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

    public static int insertDataIntoDatabase(String pwd, String name, String mobileNumber, String bloodGroup) {
        try {
            String jdbcUrl = "jdbc:mysql://localhost:3306/blood";
            String dbUsername = "root";
            String dbPassword = "sqlsme";

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


}
