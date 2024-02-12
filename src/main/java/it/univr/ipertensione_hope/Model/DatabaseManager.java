package it.univr.ipertensione_hope.Model;

import java.sql.*;

public class DatabaseManager {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bloodmonitor";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345";
    private static Connection connection;

    // Crea una connessione con il db e la tiene salvata
    public static boolean connect() {
        try {
            DatabaseManager.connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            return true;
        } catch(SQLException e){
            return false;
        }
    }

    public static void disconnect() {
        // chiudi la connessione con il database
    }

    // Recupera uno o più elementi dalle tabelle
    public static ResultSet getItem(String query) {
        try {
            PreparedStatement stat = connection.prepareStatement(query);
            return stat.executeQuery();
        } catch (SQLException e) {
            System.out.println("Query fallita");
            return null;
        }
    }

    // Aggiorna il db
    public static boolean updateItem(String query) {
        try {
            PreparedStatement stat = connection.prepareStatement(query);
            stat.executeUpdate();
            return true;
        } catch (SQLException e){
            System.out.println("Query fallita");
            return false;
        }
    }
}