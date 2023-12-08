package it.univr.Model;

import java.sql.*;

public class DatabaseManager {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bloodmonitor";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345";

    public static ResultSet executeQuery(String query, Object... parameters) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set parameters
            for (int i = 0; i < parameters.length; i++) {
                preparedStatement.setObject(i + 1, parameters[i]);
            }

            // Execute the query
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Return null in case of an exception
        return null;
    }
}