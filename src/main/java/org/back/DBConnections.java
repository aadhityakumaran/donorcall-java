package org.back;

import java.sql.*;
import java.util.*;

public class DBConnections {

    static String jdbcUrl = "jdbc:mysql://localhost:3306/blood";
    static String dbUsername = "root";
    static String dbPassword = "sqlsme";
    public static boolean isValidLogin(int donorID, String password) {
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);

            String selectQuery = "SELECT * FROM users WHERE donor_id = ? AND pwd = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, donorID);
            preparedStatement.setBytes(2, Utils.hashed(password));

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
            int donor_id = rand.nextInt(1000,10000);
            String insertQuery = "INSERT INTO users (donor_id, pwd, phone, name, blood) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, donor_id);
            preparedStatement.setBytes(2, Utils.hashed(pwd));
            preparedStatement.setString(3, mobileNumber);
            preparedStatement.setString(4, name);
            preparedStatement.setString(5, bloodGroup);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted == 0)
                return -1;
            preparedStatement.close();
            connection.close();

            return donor_id;
        } catch (SQLIntegrityConstraintViolationException ex) {
            return registerNewUser(pwd, name, mobileNumber, bloodGroup);
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
            int patient_id = rand.nextInt(1000, 10000);
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
        } catch (SQLIntegrityConstraintViolationException ex) {
            return registerNewPatient(name, age, bloodGroup, mobileNumber, info);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public static String getBloodGroup(int donorID) {
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);

            String selectQuery = "SELECT blood FROM users WHERE donor_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, donorID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("blood");
            }
            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static Object[][] getDonatables(int donorID) {
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);

            String blood = getBloodGroup(donorID);

            String selectQuery = "SELECT patient_id, name, age, blood, phone, info FROM hospital WHERE blood = ?";
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            statement.setString(1, blood);

            ResultSet resultSet = statement.executeQuery();
//            // Get the ResultSet metadata to determine the number of columns
            int numColumns = resultSet.getMetaData().getColumnCount();

            List<Object[]> resultList = new ArrayList<>();
            while (resultSet.next()) {
                Object[] row = new Object[numColumns];
                for (int col = 1; col <= numColumns; col++) {
                    row[col - 1] = resultSet.getObject(col);
                }
                resultList.add(row);
            }

            resultSet.close();
            statement.close();
            connection.close();

            return resultList.toArray(new Object[0][0]);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static Object[][] getPatients() {
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);

            String selectQuery = "SELECT * FROM hospital";
            PreparedStatement statement = connection.prepareStatement(selectQuery);

            ResultSet resultSet = statement.executeQuery();
//            // Get the ResultSet metadata to determine the number of columns
            int numColumns = resultSet.getMetaData().getColumnCount();

            List<Object[]> resultList = new ArrayList<>();
            while (resultSet.next()) {
                Object[] row = new Object[numColumns];
                for (int col = 1; col <= numColumns; col++) {
                    row[col - 1] = resultSet.getObject(col);
                }
                resultList.add(row);
            }

            resultSet.close();
            statement.close();
            connection.close();

            return resultList.toArray(new Object[0][0]);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void updateDonorDate(int donor_id) {
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            String query = "UPDATE users SET last_don = ? WHERE donor_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
            preparedStatement.setDate(1, currentDate);
            preparedStatement.setInt(2, donor_id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void removePatient(int patient_id) {
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            String insertQuery = "DELETE FROM hospital WHERE patient_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, patient_id);

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
