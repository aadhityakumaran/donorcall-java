package org.back;

import java.sql.*;

public class Donor_back {


    public static boolean isValidLogin(String username, String password) {
        try {
            String jdbcUrl = "jdbc:mysql://localhost:3306/blood";
            String dbUsername = "root";
            String dbPassword = "tang";

            Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);

            String selectQuery = "SELECT * FROM users WHERE username = ? AND password_hash = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, username);
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
    public static boolean registerNewUser(String username, String password) {
        try {
            String jdbcUrl = "jdbc:mysql://localhost:3306/blood";
            String dbUsername = "root";
            String dbPassword = "tang";

            Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            connection.setAutoCommit(false); // Disable auto-commit

            String insertQuery = "INSERT INTO users (username, password_hash) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            int rowsInserted = preparedStatement.executeUpdate();

            // Commit the transaction
            connection.commit();

            // Enable auto-commit and close resources
            connection.setAutoCommit(true);
            preparedStatement.close();
            connection.close();

            return rowsInserted > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
