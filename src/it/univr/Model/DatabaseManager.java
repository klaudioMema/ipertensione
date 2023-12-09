package it.univr.Model;

import java.sql.*;

public class DatabaseManager {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bloodmonitor";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345";

    // Crea una connessione con il db
    public static Connection connect() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)){
            System.out.println("Connesso con il database: " + connection);
            return connection;
        } catch(SQLException e){
            return null;
        }
    }

    // Recupera uno o più elementi dalle tabelle
    public static ResultSet getItem(String query) {
        try  {
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            PreparedStatement stat = connection.prepareStatement(query);
            ResultSet rs = stat.executeQuery();

            return rs;
        } catch (SQLException e) {
            System.out.println("Query fallita");
            return null;
        }

    }

    // Aggiorna il db
    public static boolean updateItem(String query) {
        Connection connection = connect();
        if(connection == null){
            System.out.println("Connessione con il db fallita");
            return false;
        }
        else {
            try (PreparedStatement stat = connection.prepareStatement(query)) {
                stat.executeUpdate();
                connection.close();
                stat.close();
                return true;
            } catch (SQLException e){
                System.out.println("Query fallita");
                return false;
            }
        }
    }
}