package it.univr.ipertensione_hope.Controller;

import java.sql.*;

public class DatabaseController {

    private static final String url = "jdbc:mysql://localhost:3306/bloodmonitor";
    private static final String user = "root";
    private static final String pass = "12345";

    // Create a new connection
    public static Connection connect() throws SQLException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url,user,pass);
        } catch (Exception e ){
            e.printStackTrace();
        }

        if(conn != null) {
            System.out.println("Connected");
            return conn;
        }
        return null;
    }

    // Get a result set
    public static ResultSet getResultSet(String query) {
        ResultSet rs = null;
        try {
            Connection conn = connect();
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(query);
        } catch (SQLException e){
            e.printStackTrace();
        }

        return rs;
    }

    // Execute a query
    public static boolean updateItem(String query) {
        try {
            Connection conn  = connect();
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
            conn.close();
            statement.close();
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
